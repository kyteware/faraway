#![allow(unused)]

use std::{fmt::Write, ops::{Add, Mul, Sub}};

use faraway::{Color, Vec3, Vec4, Triangle};

const WIDTH: usize = 54;
const HEIGHT: usize = 36;

fn main() {
    // step 1
    let t1 = Triangle(
        Vec3::new(-1.0, 0.0, 2.0),
        Vec3::new(1.0, 0.0, 2.0),
        Vec3::new(0.0, -1.0, 3.0),
        Color(206)
    );
    
    let t2 = Triangle(
        Vec3::new(-1.0, -1.2, 2.8),
        Vec3::new(1.0, -1.2, 2.8),
        Vec3::new(0.0, 0.0, 1.4),
        Color(23)
    );

    let cam = Camera::new(
        Vec3::new(0.0, -0.5, -1.0),
        Orientation {
            yaw: 3.0,
            pitch: 2.0,
        },
    );

    let screen = render(cam, vec![t2]);
    display_screen(screen);
}

fn render(camera: Camera, shapes: Vec<Triangle>) -> [[(f32, Color); WIDTH]; HEIGHT] {
    let mut screen = [[(f32::NAN, Color::BLACK); WIDTH]; HEIGHT];
    let s = camera.origin;
    for iy in 0..HEIGHT {
        for ix in 0..WIDTH {
            let m = Vec3::new(
                0.015 * (ix as i32 - (WIDTH as i32) / 2) as f32,
                0.015 * (iy as i32 - (HEIGHT as i32) / 2) as f32,
                1.0,
            );
            let mut opts: Vec<(f32, Color)> = Vec::with_capacity(shapes.len());
            for sn in 0..shapes.len() {
                let t = shapes[sn];
                let p = t.to_plane();
                let distance =
                    -(p.a * s.x + p.b * s.y + p.c * s.z + p.k) / (p.a * m.x + p.b * m.y + p.c * m.z);
                let intercept = m.dot(Vec3::splat(distance)) + s;

                if t.contains(intercept) {
                    opts.push((1.0/distance, t.3));
                }
            }
            if opts.len() > 0 {
                println!("choices are {:?}", opts);
                let mut choice = opts[0];
                for opt in opts{
                    if opt.0 > choice.0 {
                        choice = opt 
                    }
                }
                println!("chose {:?}", choice);
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
