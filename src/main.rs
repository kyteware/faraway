#![allow(unused)]

use std::{fmt::Write, ops::{Add, Mul, Sub}};

const WIDTH: usize = 54;
const HEIGHT: usize = 36;

fn main() {
    // step 1
    let t1 = Triangle(
        Vec3::new(-1.0, 0.0, 2.0),
        Vec3::new(1.0, 0.0, 2.0),
        Vec3::new(0.0, -2.0, 3.0),
        Color(206)
    );
    
    let t2 = Triangle(
        Vec3::new(-1.0, -2.0, 1.5),
        Vec3::new(1.0, -1.0, 1.0),
        Vec3::new(0.0, 0.0, 1.5),
        Color(23)
    );

    let cam = Camera::new(
        Vec3::new(0.0, -1.0, -1.0),
        Orientation {
            yaw: 3.0,
            pitch: 2.0,
        },
    );

    let screen = render(cam, vec![t2, t1]);
    display_screen(screen);
}

fn render(camera: Camera, shapes: Vec<Triangle>) -> [[(f32, Color); WIDTH]; HEIGHT] {
    let mut screen = [[(f32::NAN, Color::BLACK); WIDTH]; HEIGHT];
    let s = camera.origin;
    for iy in 0..HEIGHT {
        for ix in 0..WIDTH {
            let m = Vec3::new(
                0.025 * (ix as i32 - (WIDTH as i32) / 2) as f32,
                0.025 * (iy as i32 - (HEIGHT as i32) / 2) as f32,
                1.0,
            );
            let mut opts: Vec<(f32, Color)> = Vec::with_capacity(shapes.len());
            for sn in 0..shapes.len() {
                let t = shapes[sn];
                let p = Vec4::from(t);
                let distance =
                    -(p.a * s.x + p.b * s.y + p.c * s.z + p.k) / (p.a * m.x + p.b * m.y + p.c * m.z);
                let intercept = m.dot(Vec3::splat(distance)) + s;

                if t.contains(intercept) {
                    opts.push((1.0/distance, t.3));
                }
            }
            if opts.len() > 0 {
                let mut choice = opts[0];
                for opt in opts{
                    if opt.0 > choice.0 {
                        choice = opt 
                    }
                }
                screen[iy][ix] = choice;
            }
        }
    }
    screen
}

fn display_screen(a: [[(f32, Color); WIDTH]; HEIGHT]) {
    for row in a {
        for pixel in row {
            // if pixel.0.is_nan() {
            //     print!("  ");
            // } else {
            //     print!("██")
            // }
            print!("{}", pixel.1.enclose(String::from("██")));
        }
        println!();
    }
}

/// A 3 float vector.
#[derive(Clone, Copy, Debug)]
struct Vec3 {
    x: f32,
    y: f32,
    z: f32,
}

impl Vec3 {
    fn new(x: f32, y: f32, z: f32) -> Vec3 {
        Vec3 { x, y, z }
    }
    fn dot(self, other: Vec3) -> Vec3 {
        Vec3::new(self.x * other.x, self.y * other.y, self.z * other.z)
    }
    fn splat(n: f32) -> Vec3 {
        Vec3::new(n, n, n)
    }
    fn sum(self) -> f32 {
        self.x + self.y + self.z
    }
}

impl Add for Vec3 {
    type Output = Vec3;
    fn add(self, other: Vec3) -> Vec3 {
        Vec3::new(self.x + other.x, self.y + other.y, self.z + other.z)
    }
}

impl Sub for Vec3 {
    type Output = Vec3;
    fn sub(self, other: Vec3) -> Vec3 {
        Vec3::new(self.x - other.x, self.y - other.y, self.z - other.z)
    }
}

impl Mul for Vec3 {
    type Output = Vec3;
    fn mul(self, other: Vec3) -> Vec3 {
        Vec3::new(
            self.y * other.z - self.z * other.y,
            self.z * other.x - self.x * other.z,
            self.x * other.y - self.y * other.x,
        )
    }
}

/// A 4 float vector.
#[derive(Clone, Copy, Debug)]
struct Vec4 {
    a: f32,
    b: f32,
    c: f32,
    k: f32,
}

impl Vec4 {
    fn new(a: f32, b: f32, c: f32, k: f32) -> Vec4 {
        Vec4 { a, b, c, k }
    }
    /// escape from the matrix
    fn eftm(p: Vec3, k: f32) -> Vec4 {
        Vec4::new(p.x, p.y, p.z, k)
    }
    fn as_abc(self) -> Vec3 {
        Vec3::new(self.a, self.b, self.c)
    }
}

impl From<Triangle> for Vec4 {
    fn from(t: Triangle) -> Self {
        let a = t.1 - t.0;
        let b = t.2 - t.0;

        let n = a * b; // normal
        let s = Vec3::new(-1.0, 0.0, 2.0);

        let k = n.dot(Vec3::splat(-1.0)).dot(s).sum();
        Vec4::eftm(n, k)
    }
}

/// A triange made up of any 3 points in the 3d coordinate plane.
#[derive(Clone, Copy, Debug)]
struct Triangle(Vec3, Vec3, Vec3, Color);

impl Triangle {
    fn contains(&self, p: Vec3) -> bool {
        p.x > self.0.x.min(self.1.x.min(self.2.x))
            && p.x < self.0.x.max(self.1.x.max(self.2.x))
            && p.y > self.0.y.min(self.1.y.min(self.2.y))
            && p.y < self.0.y.max(self.1.y.max(self.2.y))
            && p.z > self.0.z.min(self.1.z.min(self.2.z))
            && p.z < self.0.z.max(self.1.z.max(self.2.z))
            && {
                // check line 12
                let v = self.1 - self.2;
                let a = v * (p - self.2);
                let b = v * (self.0 - self.2);
                let c = a.dot(b).sum();
                c > 0.0
            }
            && {
                // check line 02
                let v = self.0 - self.2;
                let a = v * (p - self.2);
                let b = v * (self.1 - self.2);
                let c = a.dot(b).sum();
                c > 0.0
            }
            && {
                // check line 01
                let v = self.1 - self.0;
                let a = v * (p - self.0);
                let b = v * (self.2 - self.0);
                let c = a.dot(b).sum();
                c > 0.0
            }
    }
}

#[derive(Clone, Copy, Debug)]
struct Color(u8);

impl Color {
    fn enclose(self, s: String) -> String {
        let mut n = String::new();
        write!(n, "\x1b[38;5;{}m{}\x1b[0m", self.0, s);
        n
    }
}

impl Color {
    const BLACK: Color = Color(16);
}

/// An orientation of an object.
#[derive(Clone, Copy, Debug)]
struct Orientation {
    yaw: f32,
    pitch: f32,
}

/// A camera in the 3d plane.
#[derive(Clone, Copy, Debug)]
struct Camera {
    origin: Vec3,
    orientation: Orientation,
}

impl Camera {
    fn new(origin: Vec3, orientation: Orientation) -> Self {
        Self {
            origin,
            orientation,
        }
    }
}

mod tests {
    use crate::{Triangle, Vec3, Color};

    #[test]
    fn triangle_contains() {
        let t = Triangle(
            Vec3::new(-1.0, 0.0, 2.0),
            Vec3::new(1.0, 0.0, 2.0),
            Vec3::new(0.0, -1.0, 3.0),
            Color(0)
        );
        let i = Vec3::new(0.5, -0.5, 2.5);
        let p = Vec3::new(100.0, 100.0, 100.0);

        assert!(t.contains(i));
        assert!(!t.contains(p));
    }
}
