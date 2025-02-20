package domain;

import exception.NotNumberPositiveException;

public class TryNumber {
    private int number;

    public TryNumber(final int number) {
        validatePositiveMoveCount(number);
        this.number = number;
    }

    public boolean isTryable() {
        return number > 0;
    }

    public void decrease() {
        number --;
    }

    private void validatePositiveMoveCount(final int number) {
        if (number <= 0) {
            throw new NotNumberPositiveException();
        }
    }
}
