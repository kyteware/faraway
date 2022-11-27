use std::ops::{Add, Sub, Mul};

/// A 3 float vector.
#[derive(Clone, Copy, Debug)]
pub struct Vec3 {
    pub x: f32,
    pub y: f32,
    pub z: f32,
}

impl Vec3 {
    pub fn new(x: f32, y: f32, z: f32) -> Vec3 {
        Vec3 { x, y, z }
    }
    pub fn dot(self, other: Vec3) -> Vec3 {
        Vec3::new(self.x * other.x, self.y * other.y, self.z * other.z)
    }
    pub fn splat(n: f32) -> Vec3 {
        Vec3::new(n, n, n)
    }
    pub fn sum(self) -> f32 {
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
pub struct Vec4 {
    pub a: f32,
    pub b: f32,
    pub c: f32,
    pub k: f32,
}

impl Vec4 {
    pub fn new(a: f32, b: f32, c: f32, k: f32) -> Vec4 {
        Vec4 { a, b, c, k }
    }
    /// escape from the matrix
    pub fn eftm(p: Vec3, k: f32) -> Vec4 {
        Vec4::new(p.x, p.y, p.z, k)
    }
    pub fn as_abc(self) -> Vec3 {
        Vec3::new(self.a, self.b, self.c)
    }
}

