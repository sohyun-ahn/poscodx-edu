class Rect {
  constructor(width, height) {
    this.width = width;
    this.height = height;
  }

  draw() {
    console.log(
      `Rect: width=${this.width}, height=${this.height}을 그렸습니다`
    );
  }
}

// test
const rect1 = new Rect(10, 20);
const rect2 = new Rect(100, 200);
rect1.draw();
rect2.draw();

/**
 *  cf: 생성자 함수(prototype 기반)
 *
 */

const Circle = function (x, y, r) {
  this.x = x;
  this.y = y;
  this.r = r;
};

Circle.prototype.draw = function () {
  console.log(`Circle: x=${this.x}, y=${this.y}, r=${this.r}을 그렸습니다`);
};

// test
const circle1 = new Circle(50, 50, 20);
circle1.draw();

/**
 * extends
 */
class Shape {
  constructor(fillColor, lineColor) {
    this.fillColor = fillColor;
    this.lineColor = lineColor;
  }

  draw() {
    console.log("구현할 수 없습니다");
  }
}

class RectTriangle extends Shape {
  constructor(fillColor, lineColor, width, height, angle) {
    super(fillColor, lineColor);
    this.width = width;
    this.height = height;
    this.angle = angle;
  }

  //@Override
  draw() {
    console.log(
      `RectTriangle: fillColor=${this.fillColor}, lineColor=${this.lineColor}, width=${this.width}, height=${this.height}, angle=${this.angle}을 그��습니다`
    );
  }
}

const shape1 = new RectTriangle("blue", "gray", 10, 10, 30);
shape1.draw();
