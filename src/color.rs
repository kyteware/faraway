use std::fmt::Write;

#[derive(Clone, Copy, Debug)]
pub struct Color(pub u8);

impl Color {
    pub const BLACK: Color = Color(16);
    pub fn enclose(self, s: String) -> String {
        let mut n = String::new();
        write!(n, "\x1b[38;5;{}m{}\x1b[0m", self.0, s);
        n
    }
}
