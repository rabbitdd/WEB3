package validators;

import com.sun.org.apache.bcel.internal.generic.INEG;
import exceptions.InvalidException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@FacesValidator("validatorForY")
public class ValidatorForY implements Validator, DataCheck {
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try {
            isValid(value.toString());
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Incorrect Data Y or NULL data");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public void isValid(String y) throws InvalidException {
        double current = Double.parseDouble(y);
        String[] args = new String[128];
        if (y.contains(".")) {
            args = y.split("\\.");
            //log(args[0] + args[1]);
            Pattern pattern = Pattern.compile("[1-9]");
            Pattern patternAlpha = Pattern.compile("[a-zA-Z]");
            //System.out.println(decimalFormat.format(Boolean.parseBoolean(y)));
            if (Math.abs(Integer.parseInt(args[0])) >= 3 && ((pattern.matcher(args[1]).find())||(patternAlpha.matcher(args[1]).find()))) {
                //|| !rArea.contains(decimalFormat.format(Boolean.parseBoolean(r)))
                //|| !yArea.contains(decimalFormat.format(Boolean.parseBoolean(y)))) {
                throw new InvalidException();
            }
        } else if (Integer.parseInt(y) > 3) {
            throw new InvalidException();
        }
    }
}
