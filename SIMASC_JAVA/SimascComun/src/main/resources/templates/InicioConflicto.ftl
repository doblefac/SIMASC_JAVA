			<hr class="center">
            <table id="tiempo" class="tiempo">
                <tr>
                    <td  class="titulofooter " id="pregunta"><!--rowspan="7"-->
						¿Cuánto hace que se inició el conflicto?
					</td>
					<td>
						<table>
							<tr>
								<td class="colorfooter">De 1 a 30 dias</td><td><#if Object.inicioConflicto=="1">X</#if></td>
							</tr>
							<tr>
								<td class="colorfooter">Entre 2 y 6 meses</td><td><#if Object.inicioConflicto=="2">X</#if></td>
							</tr>
							<tr>
								<td class="colorfooter">Entre 7 y 12 meses</td><td><#if Object.inicioConflicto=="3">X</#if></td>
							</tr>
							<tr>
								<td class="colorfooter">Mas de 1 año</td><td><#if Object.inicioConflicto=="4">X</#if></td>
							</tr>
							<tr>
								<td class="colorfooter">No informa</td><td><#if Object.inicioConflicto=="5">X</#if></td>
							</tr>
						</table>
					</td>
                </tr>
            </table>