# carp-code-generator
generate JPA entity, repository etc. from database table with carp style.


## Java style useage
1. major configuration are under `generator.properties`

2. you can create `generatorExtend.properties` in classpath to override configuration

3. normal main class should looks like:
```
public static void main(String[] args) {
        JpaGenerator generator = new JpaGenerator();
        generator.rendEntityAndRepository();
        //using follow code,if you just want print out classes info in console.
        //generator.rendEntityAndRepository(true);
    }
```

4. you can redefine `${jpa.entityTemplateName}` and `${jpa.repositoryTemplateName}`
and put them under `${template.dir}/${template.type}` to generator your own class.

5. please reference `EntityDto.java` and `EntityAttributeDto.java` for properties can be used in templates.
