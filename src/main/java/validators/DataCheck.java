package validators;

import exceptions.InvalidException;

public interface DataCheck {
    public abstract void isValid(String string) throws InvalidException;
}
