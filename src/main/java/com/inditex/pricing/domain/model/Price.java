package com.inditex.pricing.domain.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@RequiredArgsConstructor
public class Price {
    private Long brandId;
    private Long productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Integer priority;
    private BigDecimal price;
    private String currency;

}

// Nota para el revisor:
// Actualmente el modelo de dominio Price actúa principalmente como un data holder, ya que la lógica del caso de uso es muy simple.
// En un escenario más complejo, aquí se incluirían métodos de negocio, como validaciones, comprobaciones etc.
// Como por ejemplo decisiones sobre qué precio aplicar en función de la prioridad, pero eso ya lo hace la misma query y si por ejemplo mañana cambias a otra base de datos,
// seguramente siempre se va a querer delegarlo a la query aunque tengamos un método aqui para no depender de si cambiamos o no de base de datos, por lo que sería redundante.
// En este caso concreto, he optado por mantener el modelo simple, siguiendo el principio YAGNI (mantener el dominio limpio hasta que
// emerjan reglas de negocio que realmente justifiquen el tener que poner métodos de dominio).

