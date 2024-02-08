package step.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import step.learning.services.HashService;
import step.learning.services.Md5HashService;
import step.learning.services.Sha1HashService;
import step.learning.services.rnd.CodeGen;
import step.learning.services.rnd.DigitCodeGen;

import java.util.Random;

/**
 * Binding interfaces and classes for services
 */
public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
//        bind(HashService.class).to(Md5HashService.class);
//        bind(HashService.class).to(Sha1HashService.class);
        bind(HashService.class)
                .annotatedWith(Names.named("hash-160"))
                .to(Sha1HashService.class);
        bind(HashService.class)
                .annotatedWith(Names.named("hash-128"))
                .to(Md5HashService.class);

        bind(Random.class).toInstance(new Random());
        bind(CodeGen.class).to(DigitCodeGen.class);
    }
}
