package my.library;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value="crypto.currency", havingValue="xrp")
public class XRPCurrency implements Currency, InitializingBean {

    @Override
    public String abbreviation() {
        return "XRP";
    }

    @Override
    public String fullName() {
        return "Ripple";
    }

    @Override
    public double exchangeRate2Dollar() {
        return 0.47192; // 23.02.2021 2016
    }

    @Override
    public String type() {
        return "crypto";
    }

    public double exchangeRate2BTN() {
        return 0.00000452;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String mess = fullName() + "(" + abbreviation() + ")" + " is " + type() + " money";
        System.out.println(mess);
    }
}