# Quick Review of Important Spring Concepts

| Concept | Description |
| --- | --- |
| Dependency
Injection | Identify beans, their dependencies and wire them together (provides IOC - Inversion of Control) |
| Constr.
injection | Dependencies are set by creating the Bean using its Constructor |
| Setter
injection | Dependencies are set by calling setter methods on your beans |
| Field
injection | No setter or constructor. Dependency is injected using reflection. |
| IOC
Container | Spring IOC Context that manages Spring beans & their lifecycle |
| Bean
Factory | Basic Spring IOC Container |
| Application
Context | Advanced Spring IOC Container with enterprise-specific features - Easy to use in web
applications with internationalization features and good integration with Spring AOP |
| Spring
Beans | Objects managed by Spring |