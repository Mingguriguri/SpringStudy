# Quick Review: Spring Framework Annotation

| Annotation | Description |
| --- | --- |
| @Configuration |  Indicates that a class declares one or more @Bean methods and may be processed by the
Spring container to generate bean definitions |
| @ComponentScan | Define specific packages to scan for components. If specific packages are not defined,
scanning will occur from the package of the class that declares this annotation |
| @Bean | Indicates that a method produces a bean to be managed by the Spring container |
| @Component | Indicates that an annotated class is a "component”
@Component 클래스가 ComponentScan에 속한다면 Spring Bean이 생성됨 |
| @Service | Specialization of @Component indicating that an annotated class has business logic |
| @Controller | Specialization of @Component indicating that an annotated class is a "Controller" (e.g. a
web controller). Used to define controllers in your web applications and REST API |
| @Repository | Specialization of @Component indicating that an annotated class is used to retrieve
and/or manipulate data in a database |
| @Primary | Indicates that a bean should be given preference when multiple candidates are qualified to autowire a singlevalued dependency |
| @Qualifier | Used on a field or parameter as a qualifier for candidate beans when autowiring
모든 컴포넌트에 한정자를 추가할 수 있고 자동 연결 시 한정자를 사용할 수 있음 |
| @Lazy | Indicates that a bean has to be lazily initialized. Absence of @Lazy annotation will lead to eager initialization. |
| @Scope (value =ConfigurableBeanFactory.SCOPE_PROTOTYPE) | Defines a bean to be a prototype - a new instance will be created every time you refer to the bean. Default scope is singleton - one instance per IOC container. |
| @PostConstruct | Identifies the method that will be executed after dependency injection is done to perform
any initialization
의존성 주입이 수행된 이후 초기화를 위해 실행될 메서드를 나타냄 |
| @PreDestroy | Identifies the method that will receive the callback notification to signal that the instance is
in the process of being removed by the container. Typically used to release resources that it has been holding.
컨테이너에서 인스턴스를 삭제하는 과정을 거치고 있음을 알려주는 콜백 |
| @Named | Jakarta Contexts & Dependency Injection (CDI) Annotation similar to Component |
| @Inject | Jakarta Contexts & Dependency Injection (CDI) Annotation similar to Autowired |