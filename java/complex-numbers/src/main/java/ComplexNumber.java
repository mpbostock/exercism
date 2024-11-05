import static java.lang.Math.*;

class ComplexNumber {
    private final double real;
    private final double imaginary;

    ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    double getReal() {
        return real;
    }

    double getImaginary() {
        return imaginary;
    }

    double abs() {
        return sqrt(sumOfSquares());
    }

    ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(real + other.real, imaginary + other.imaginary);
    }

    ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(real - other.real, imaginary - other.imaginary);
    }

    ComplexNumber multiply(ComplexNumber other) {
        return new ComplexNumber(
                real * other.real - imaginary * other.imaginary,
                imaginary * other.real + real * other.imaginary
        );
    }

    ComplexNumber divide(ComplexNumber other) {
        double sumOfSquares = other.sumOfSquares();
        return new ComplexNumber(
                (real * other.real + imaginary * other.imaginary) / sumOfSquares,
                (imaginary * other.real - real * other.imaginary) / sumOfSquares
        );
    }

    ComplexNumber conjugate() {
        return new ComplexNumber(real, -imaginary);
    }

    ComplexNumber exponentialOf() {
        return new ComplexNumber(exp(real) * cos(imaginary), exp(real) * sin(imaginary));
    }

    private double sumOfSquares() {
        return real * real + imaginary * imaginary;
    }

}