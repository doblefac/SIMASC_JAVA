<hr class="center">
            <table class="tituloConvocante center">
                <tr>                   
                    <td>Convocado</td>
                </tr>
            </table>
            <table class="persona">
                <tr>
                    <td class="colortd">Rol:</td>
                    <td class="pInfotd">${Object.rol}</td>
                    <td class="colortd">Tipo de persona:</td>
                    <td class="pInfotd">${Object.tipoPersonaParte}</td>
                 </tr>
                <tr>
                    <td class="colortd">Tipo de identificación:</td>
                    <td class="pInfotd">${Object.tipoIdentificacion}</td>
                    <td class="colortd">Numero de identificación:</td>
                    <td class="pInfotd">${Object.numeroIdentificacion}</td>
                </tr>
                <tr>
                    <td class="colortd">Ciudad identificación:</td>
                    <td class="pInfotd">${Object.ciudadIdentificacion?capitalize}</td>
                    <td class="colortd">Nacionalidad:</td>
                    <td class="pInfotd">${Object.nacionalidad?capitalize}</td>
				</tr>
                <tr>                    
                    <td class="colortd">Nombre:</td>
                    <td class="pInfotd">${Object.nombre?capitalize}</td>
                </tr>
                <tr>
                    <td class="colortd">Dirección:</td>
                    <td class="pInfotd">${Object.direccion}</td>
                 </tr>
                <tr>
                    <td class="colortd">Pais:</td>
                    <td class="pInfotd">${Object.pais?capitalize}</td>
                    <td class="colortd">Ciudad:</td>
                    <td class="pInfotd">${Object.ciudad?capitalize}</td>
                </tr>
                <tr>
                    <td class="colortd">Teléfono:</td>
                    <td class="pInfotd">${Object.telefono}</td>
                    <td class="colortd">Celular:</td>
                    <td class="pInfotd">${Object.celular}</td>
                </tr>
                <tr>
                    <td class="colortd">Email:</td>
                    <td class="pInfotd">${Object.email}</td>
                     <td class="colortd">Email2:</td>
                    <td class="pInfotd">${Object.email2}</td>
                </tr>
                <tr>   
                <td class="colortd">Email3:</td>
                    <td class="pInfotd">${Object.email3}</td> 
                    <td class="colortd">Sexo:</td>
                    <td class="pInfotd">${Object.sexo?capitalize}</td>
                </tr>
                <tr>
                    <td class="colortd">Fecha de nacimiento:</td>
                    <td class="pInfotd">${Object.fechaNacimiento}</td>
                    <td class="colortd">Estrato:</td>
                    <td class="pInfotd">${Object.estrato?capitalize}</td>
                </tr>
                <tr>
                    <td class="colortd">Profesion:</td>
                    <td class="pInfotd">${Object.profesion?capitalize}</td>
                    <td class="colortd">Escolaridad:</td>
                    <td class="pInfotd">${Object.profesion?capitalize}</td>
                </tr>
                <tr>
                    
                    <td class="colortd">Institución educativa:</td>
                    <td class="pInfotd">${Object.institucionEducativa?capitalize}</td>
                    <td class="colortd">Fecha de grado:</td>
                    <td class="pInfotd">${Object.fechaGrado}</td>
                </tr>
                <tr>
                    <td class="colortd">Tarjeta profesional:</td>
                    <td class="pInfotd">${Object.tarjetaProfesional}</td>
                    <td class="colortd">Entidad que expide la tarjeta profesional:</td>
                    <td class="pInfotd">${Object.entidadTarjetaProfesional?capitalize}</td>                  
                </tr>
            </table>