package client;

import my.library.ETHCurrency;
import my.library.EURCurrency;
import my.library.XRPCurrency;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    @ConditionalOnMissingBean(XRPCurrency.class)
    public void sellXRP() {

        System.out.println("You should sell ripple");
    }

    @Bean
    @ConditionalOnMissingBean(ETHCurrency.class)
    public void sellETH() {
        System.out.println("You should sell ethereum");
    }

    @Bean
    @ConditionalOnMissingBean(EURCurrency.class)
    public void sellEUR() {
        System.out.println("You should sell euro");
    }

    @Bean
    @ConditionalOnBean(XRPCurrency.class)
    public void buyXRP() {
        System.out.println("You should buy ripple");
    }

    @Bean
    @ConditionalOnBean(ETHCurrency.class)
    public void buyETH() {
        System.out.println("You should buy ethereum");
    }

    @Bean
    @ConditionalOnBean(EURCurrency.class)
    public void buyEUR() {
        System.out.println("You should buy euro");
    }


}
