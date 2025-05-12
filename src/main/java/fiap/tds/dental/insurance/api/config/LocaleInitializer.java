package fiap.tds.dental.insurance.api.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.util.Locale;
import org.springframework.context.i18n.LocaleContextHolder;

@Component
public class LocaleInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        LocaleContextHolder.setLocale(new Locale("pt", "BR"));
        System.out.println("Locale inicializado para pt-BR");
    }
}

