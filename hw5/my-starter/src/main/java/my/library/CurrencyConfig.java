package my.library;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = CurrencyConfig.class)
public class CurrencyConfig {

    @Bean
    @ConditionalOnProperty(value = "fiat.currency", havingValue = "eur")
    Currency euro() {
        return new EURCurrency();
    }

    @Bean
    @ConditionalOnProperty(value = "crypto.currency", havingValue = "eth")
    Currency eth() {
        return new ETHCurrency();
    }

    @Bean
    @ConditionalOnProperty(value = "crypto.currency", havingValue = "xrp")
    Currency xrp() {
        return new XRPCurrency();
    }

}
