import com.model.CreditCard;
import com.service.impl.CardValidator;
import org.junit.Test;

import javax.xml.soap.SOAPException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CardValidatorTest {

    @Test
    public void shouldCheckCreditCardValidity() throws SOAPException {
        CardValidator cardValidator = new CardValidator();

        CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");
        assertTrue("Кредитная карта должна быть действительна", cardValidator.validate(creditCard));

        creditCard.setNumber("12341233");
        assertFalse("Кредитная карта должна быть недействительна", cardValidator.validate(creditCard));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowANumberFormatException() throws SOAPException/*, CardValidatorException*/ {
        new CardValidator().validate(null);
    }

}