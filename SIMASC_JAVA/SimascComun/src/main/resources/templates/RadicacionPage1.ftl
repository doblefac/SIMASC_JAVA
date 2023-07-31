		<div class="subcontenido">
            <h1 class="titulo" id="titRad">Radicación de una solicitud de
                conciliación
            </h1>
            <h2>FECHA DE RADICACIÓN:</h2>
            <p>${Object.fechaRadicacion}
            </p>
            <h2>REALICE UNA BREVE DESCRIPCIÓN DE LOS
                HECHOS O DIFERENCIAS:
            </h2>
            <p>(Escriba brevemente y de manera precisa, los hechos que han
                dado origen al conflicto, así como las diferencias o cuestiones
                materia de la conciliación).
            </p>
            <p>${Object.hechos}
            </p>
            <h2>REALICE UNA BREVE DESCRIPCIÓN DE LAS
                PRETENSIONES (CONVOCANTE):
            </h2>
            <p>(Establezca de manera precisa, cuál es su interés y lo que
                quiere lograr con la conciliación).
            </p>
            <p>${Object.pretensiones}</p>
            <h2>ESTIMACIÓN RAZONADA DE LA CUANTÍA:
            </h2>
            <p>(Indique el valor en dinero, de las diferencias objeto de la
                conciliación, siempre que éste sea determinado o determinable, en
                caso contrario, indique que son de cuantía indeterminada).
            </p>
            <p>${Object.tipoCuantia}, ${Object.valorPretensiones}
            </p>
            <h2>MATERIA:</h2>
            <p>${Object.asuntoConciliacion}
            </p>
            <h2>CENTRO DE CONCILIACIÓN SELECCIONADO:</h2>
            <p>${Object.centro?capitalize}
            </p>
            <h2>SEDE DE REALIZACIÓN DE AUDIENCIAS:</h2>
            <p>${Object.sede?capitalize}
            </p>
            <h2>CONCILIADOR SELECCIONADO:</h2>
            <p>${Object.conciliador?capitalize}
            </p>
            <p>El contenido de la solicitud de conciliación es cierta y fidedigna. La misma se entiende firmada por el solicitante o solicitantes o su apoderado.
            </p>                                  
        </div>