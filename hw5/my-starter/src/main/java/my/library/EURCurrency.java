package my.library;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value="fiat.currency", havingValue="eur")
public class EURCurrency implements Currency, InitializingBean {
    @Override
    public String type() {
        return "fiat";
    }

    @Override
    public String abbreviation() {
        return "EUR";
    }

    @Override
    public String fullName() {
        return "Euro";
    }

    @Override
    public double exchangeRate2Dollar() {
        return 1.22;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String mess = fullName() + "(" + abbreviation() + ")" + " is " + type() + " money";
        System.out.println(mess);
    }
}
