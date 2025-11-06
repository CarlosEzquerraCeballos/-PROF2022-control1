# 🧠 CHULETA DEFINITIVA – PROF (Profundización en Ingeniería de Software)

---

## ⚙️ ESTRUCTURA MAVEN

src/
├── main/
│ └── java/ → código fuente
└── test/
└── java/ → tests unitarios
pom.xml → configuración del proyecto

php-template
Copiar código

---

## 🧩 COMANDOS MAVEN

| Comando | Descripción |
|----------|-------------|
| `mvn clean` | Limpia el proyecto (borra target) |
| `mvn compile` | Compila el código fuente |
| `mvn test` | Ejecuta los tests |
| `mvn verify` | Ejecuta tests + genera informes |
| `mvn package` | Empaqueta el proyecto (.jar o .war) |
| `mvn site` | Genera documentación del proyecto |
| `mvn -q test` | Ejecuta los tests en modo silencioso |

---

## 📦 ESTRUCTURA BÁSICA `pom.xml`

```xml
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>es.upm.profundizacion</groupId>
  <artifactId>mi-proyecto</artifactId>
  <version>1.0</version>

  <dependencies>
    <!-- JUnit 5 -->
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.8.2</version>
      <scope>test</scope>
    </dependency>

    <!-- Mockito -->
    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <version>5.2.0</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <!-- Plugin Surefire -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.0.0</version>
      </plugin>

      <!-- Plugin JaCoCo -->
      <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>0.8.8</version>
        <executions>
          <execution>
            <goals>
              <goal>prepare-agent</goal>
            </goals>
          </execution>
          <execution>
            <id>report</id>
            <phase>verify</phase>
            <goals>
              <goal>report</goal>
            </goals>
          </execution>
          <execution>
            <id>check</id>
            <goals>
              <goal>check</goal>
            </goals>
            <configuration>
              <rules>
                <rule>
                  <element>BUNDLE</element>
                  <limits>
                    <limit>
                      <counter>INSTRUCTION</counter>
                      <value>COVEREDRATIO</value>
                      <minimum>1.0</minimum> <!-- 100% cobertura -->
                    </limit>
                  </limits>
                </rule>
              </rules>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</project>



🧪 TESTS UNITARIOS (JUnit 5)
java
Copiar código
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatriculaTest {

    @Test
    void testExcepcionVectorNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Matricula(null);
        });
    }

    @Test
    void testCalculoImporte() {
        Asignatura a1 = new Asignatura("Math", 50);
        Asignatura a2 = new Asignatura("Prog", 30);
        Matricula m = new Matricula(new Asignatura[]{a1, a2});
        assertEquals(80, m.getImporte());
    }
}



🧙 MOCKS CON MOCKITO
java
Copiar código
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

class MatriculaMockTest {

    @Test
    void testImporteConMocks() {
        Asignatura a1 = mock(Asignatura.class);
        Asignatura a2 = mock(Asignatura.class);

        when(a1.getImporte()).thenReturn(40.0);
        when(a2.getImporte()).thenReturn(60.0);

        Matricula m = new Matricula(new Asignatura[]{a1, a2});
        assertEquals(100.0, m.getImporte());
    }
}
✅ when(...).thenReturn(...) → define comportamiento
✅ verify(mock) → comprueba llamadas
✅ assertThrows() → verifica excepciones
✅ @BeforeEach / @AfterEach → inicialización y limpieza



🧮 JA COCO (Cobertura de Código)
Informe HTML:
target/site/jacoco/index.html

Ejecución:
mvn verify

Si no cumples la cobertura mínima:
Rule violated for bundle: INSTRUCTION coverage is 0.85, expected minimum is 1.00




🤖 JENKINS (Integración Continua)
🔧 Pasos para crear el job
Crear proyecto tipo Maven

En “Source Code Management” → añadir repo Git

En “Build” → Goals: clean verify (o test)

Guardar y ejecutar manualmente

Ver resultados en:

Build History → Console Output

JaCoCo report si está configurado



💬 Errores típicos
Error	Causa	Solución
Plugin not found	Falta plugin en pom.xml	Añadirlo manualmente
Test failed	Test mal hecho	Revisar asserts o mocks
Rule violated for bundle	Cobertura < mínima	Añadir tests




🧰 COMANDOS GIT ÚTILES
Comando	Descripción
git clone <url>	Clona repo
git add .	Añade cambios
git commit -m "msg"	Guarda commit
git push origin main	Sube cambios
git pull	Actualiza repo
git status	Estado actual



📄 TEST COMPLETO CON MOCKS Y ASSERTS
java
Copiar código
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MatriculaTest {

    private Asignatura a1, a2;
    private Matricula m;

    @BeforeEach
    void setUp() {
        a1 = mock(Asignatura.class);
        a2 = mock(Asignatura.class);
        when(a1.getImporte()).thenReturn(20.0);
        when(a2.getImporte()).thenReturn(30.0);
        m = new Matricula(new Asignatura[]{a1, a2});
    }

    @Test
    void testImporteCorrecto() {
        assertEquals(50.0, m.getImporte());
        verify(a1).getImporte();
        verify(a2).getImporte();
    }

    @Test
    void testExcepcionVectorNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Matricula(null));
    }
}


📈 PIPELINE DE INTEGRACIÓN CONTINUA
scss
Copiar código
Commit → GitHub → Jenkins Build (mvn verify)
→ Tests (JUnit + Mockito)
→ Reporte JaCoCo → Resultado (OK/FAIL)
🧭 ERRORES FRECUENTES
Problema	Causa	Solución
NoClassDefFoundError	Falta dependencia	Añadir <dependency> correcta
Tests run: 0	Faltan @Test	Añadirlas
Unnecessary stubbing	Mock no usado	Eliminar when() innecesario
IllegalStateException	Mezcla de matchers y valores	Usar solo any() o valores reales
Jenkins no clona repo	Repo privado / URL mal	Revisar credenciales y URL



💡 CONSEJOS DE EXAMEN
Tests → src/test/java

Clases → src/main/java

Usa assertEquals, assertThrows, verify

Nunca System.out.println

Si piden mocks, usa Mockito

100% cobertura = cubrir if/else

Si Jenkins falla → revisa pom.xml