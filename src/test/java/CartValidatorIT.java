import com.model.CreditCard;
import com.service.Validator;
import com.service.impl.CardValidator;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

public class CartValidatorIT {
    @Test
    public void shouldCheckCreditCardValidity() throws MalformedURLException {

        // Публикация веб-службы SOAP
        Endpoint endpoint = Endpoint.publish("http://localhost:8080/cardValidator", new CardValidator());
        assertTrue(endpoint.isPublished());
        assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http", endpoint.getBinding().getBindingID());

        // Необходимые свойства для доступа к веб-службе
        URL wsdlDocumentLocation = new URL("http://localhost:8080/cardValidator?wsdl");
        String namespaceURI = "http://impl.service.com/";
        String servicePart = "CardValidatorService";
        String portName = "CreditCardValidator";
        QName serviceQN = new QName(namespaceURI, servicePart);
        QName portQN = new QName(namespaceURI, portName);

        // Создаем экземпляр службы
        Service service = Service.create(wsdlDocumentLocation, serviceQN);
        Validator cardValidator = service.getPort(portQN, Validator.class);

        // Вызываем веб-службу
        CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");
        assertTrue("Credit card should be valid", cardValidator.validate(creditCard));

        creditCard.setNumber("12341233");
        assertFalse("Credit card should not be valid", cardValidator.validate(creditCard));

        // Отключаем веб службу
        endpoint.stop();
        assertFalse(endpoint.isPublished());
    }
}
