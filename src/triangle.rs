use crate::{Color, Vec3, Vec4};

/// A triange made up of any 3 points in the 3d coordinate plane.
#[derive(Clone, Copy, Debug)]
pub struct Triangle(pub Vec3, pub Vec3, pub Vec3, pub Color);

impl Triangle {
    pub fn contains(&self, p: Vec3) -> bool {
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
    pub fn to_plane(self) -> Vec4 {
        let a = self.1 - self.0;
        let b = self.2 - self.0;

        let n = a * b; // normal
        let s = Vec3::new(-1.0, 0.0, 2.0);

        let k = n.dot(Vec3::splat(-1.0)).dot(s).sum();
        Vec4::eftm(n, k)
    }
    
}
