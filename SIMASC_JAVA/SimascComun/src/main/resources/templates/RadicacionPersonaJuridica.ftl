<hr class="center">
            <table class="tituloConvocante center">
                <tr>
                    <td><img class="select" src="data:image/png;base64, iVBORw0KGgoAAAANSUhEUgAAACkAAAAqCAYAAAAu9HJYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAALVSURBVFhH7Zi9alVBEMd9GZ9BH0EfQLSIhQTEIhAiQgrFLoKN2ogIFpoiRdDKKjY2QloTlahcCSF+cSFEgxgSIqu/S0bmbHb3zJ4zEQX/MITs2bv729nZ2Y8j4R/Qf0gv9Yb8vrYathafhc8PZsOH23d/G2XfXizt1+qnTpCbTxbC6tWZsHz8bHh+9HTRqDOYuhyG8/Nhd+NTCHvb1VYFOXz4KLw8OZ6EsdjysbHw/taNsLO+FH5svTObCZKpy8G9PX9pNL0MgHoYnqZscPFK0tvAfrx3JwmUslZIOos7AQwQqwAnPOJ2BpPTJq8WIeOGX5+ZGHXYVSwyvKvbXDl1oRU0CxkD4lEvMQs6DNpAk5AxIPHmLdKTFfQAJKM8bEBRDLo2c60dcu/LZuNHJGhvSfLnLwJU+sO+Pn1chtTTTIB7Kw4j+sAxOEPKXp04l4dkZFIRb8pIvRQDiq1fvzn6TuaQsuHc/TSkzoeeKxnlAMUQISD/vxmfTEPqHcXTi22AmKjBsLLYhNTB6xmLFkDARDo29bY5gtQfvVa0BRDT22vDWb+2zAYkwSsf9bbHtJMnsZoQsAKmcrB8I7k3IDkwyEcRDUgZxoq3JPY+gEizFCF1OoqtBNoXEJkhU8czbalOPACRGySmO/MCRGZIGpP/S0Y9T0CUhUytbmvnFrMCIvnNgdWdy5MeoDWAxTxZ2nH6gNYAouKOg0p7dxfQWkBU3LtR2ymoBrQLoL4RZE9BOoHnzpMW0C6AyHSeRBoidRriFK0bi60roI7F4skcWe441Ik9at3XU6q+4yDrbZHGiV0GUnNC0qIN7RTTbVEUe6qrl0qKAavu3aIYNLXiu8rlBUMUg/51b0Gi1ImIQ0BNCDCweMCYy6uaiE70bqANYAYCNPUwppMyvKanVcz9fVILkBysxQ71pTcWnmL6LMB48o++mafEYmCK8TJTjJE7KSPN9FcIPwH0tvgesA6VbgAAAABJRU5ErkJggg==" /></td>
                    <td>Persona Juridica</td>
                </tr>
            </table>
            <table class="persona">
                <tr>
                    <td class="colortd">Rol:</td>
                    <td class="pInfotd">${Object.rol}</td>
                    <td class="colortd">Tipo de empresa:</td>
                    <td class="pInfotd">${Object.tipoEmpresa?capitalize}</td>
                    <td class="colortd">Tipo de entidad publica:</td>
                    <td class="pInfotd">${Object.tipoEntidadPublica?capitalize}</td>
                </tr>
                <tr>
                    <td class="colortd">Tipo de identificación:</td>
                    <td class="pInfotd">${Object.tipoIdentificacion}</td>
                    <td class="colortd">Numero de identificación:</td>
                    <td class="pInfotd">${Object.numeroIdentificacion}</td>
                    <td class="colortd">Ciudad de la identificación:</td>
                    <td class="pInfotd">${Object.ciudadIdentificacion?capitalize}</td>
                </tr>
                <tr>
                    <td class="colortd">Nacionalidad:</td>
                    <td class="pInfotd">${Object.nacionalidad?capitalize}</td>
                    <td class="colortd">Razón social:</td>
                    <td class="pInfotd">${Object.nombre?capitalize}</td>
                    <td class="colortd">Representante legal:</td>
                    <td class="pInfotd">${Object.representanteLegal?capitalize}</td>
                </tr>
                <tr>
                    <td class="colortd">Sector de la empresa:</td>
                    <td class="pInfotd">${Object.sectorEmpresa?capitalize}</td>
                    <td class="colortd">Pagina web:</td>
                    <td class="pInfotd">${Object.paginaWeb}</td>
                    <td class="colortd">Telefono:</td>
                    <td class="pInfotd">${Object.telefono}</td>
                </tr>
                <tr>
                    <td class="colortd">Dirección:</td>
                    <td class="pInfotd">${Object.direccion}</td>
                    <td class="colortd">Pais:</td>
                    <td class="pInfotd">${Object.pais?capitalize}</td>
                    <td class="colortd">Ciudad:</td>
                    <td class="pInfotd">${Object.ciudad?capitalize}</td>
                </tr>
                <tr>
                    <td class="colortd">Email</td>
                    <td class="pInfotd">${Object.email}</td>
                    <td class="colortd"></td>
                    <td class="pNoInfotd"></td>
                    <td class="colortd"></td>
                    <td class="pNoInfotd"></td>
                </tr>
            </table>