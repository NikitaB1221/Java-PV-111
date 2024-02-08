package step.learning.ioc;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import step.learning.services.HashService;
import step.learning.services.rnd.CodeGen;

public class IocDemo {

    @Inject @Named("hash-128")
    private HashService hashServiceField;
    private final HashService hashService;
    private final CodeGen codeGen;

    @Inject
    public IocDemo(@Named("hash-160") HashService hashService, CodeGen codeGen) {
        this.hashService = hashService;
        this.codeGen = codeGen;
    }

    public void run(){
        System.out.println("Inversion of Control");
        System.out.println(hashService.hash("123"));
        System.out.println(hashServiceField.hash("123"));
//        System.out.println(hashService.hashCode() + "||" + hashServiceField.hashCode());

        System.out.println(codeGen.newCode(30));
    }
}
