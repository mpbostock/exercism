class SimpleCalculator
  ALLOWED_OPERATIONS = ['+', '/', '*'].freeze
  class UnsupportedOperation < StandardError; end

  def self.calculate(first_operand, second_operand, operation)
    raise ArgumentError if !first_operand.is_a?(Numeric) || !second_operand.is_a?(Numeric)
    raise UnsupportedOperation unless ALLOWED_OPERATIONS.include? operation

    begin
      "#{first_operand} #{operation} #{second_operand} = #{first_operand.send(operation, second_operand)}"
    rescue ZeroDivisionError
      "Division by zero is not allowed."
    end
  end
end
