<%@page import="com.mysql.jdbc.Statement"%>
<!doctype html>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="errorPage.jsp" %>
<%@ page import ="beans.Conexion" %>
<%
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    response.setHeader("Progma", "no-cache");
    response.setDateHeader("Expires", 0);
//recuperar la sesión
    HttpSession sesion_cli = request.getSession(true);
    Integer idUsuario = (Integer) sesion_cli.getAttribute("idUsuario");    
    Connection conn = (Connection) Conexion.crearConexion();
    ResultSet resultaLog;
    Statement sent;
    sent = (Statement) conn.createStatement();
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='crear_producto.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado, resultado2, resultado3;
        Statement sentencia, sentencia2, sentencia3;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        sentencia2 = (Statement) con.createStatement();
        sentencia3 = (Statement) con.createStatement();
        resultado = sentencia.executeQuery("SELECT * FROM tipo_producto");
        resultado3 = sentencia3.executeQuery("SELECT a.almacenID, a.ubicacion FROM almacen a");
        resultado2 = sentencia2.executeQuery("SELECT * FROM proveedor pr");
%>
<html>
    <head>
        <title>Producto</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <link rel="stylesheet" href="css/main.css" />
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <script language="javascript">
            function CompararFechas() {

                var fechaEla = document.getElementById("fechaEla").value;
                var fechaNtf = document.getElementById("fechaNtf").value;
                var hoy = new Date();
                var fechaVcto = document.getElementById("fechaVcto").value;
                if ((new Date(fechaEla).getTime() >= new Date(fechaVcto).getTime())) {
                    alert("Fecha de fabricación no puede ser mayor o igual a la del vencimiento");
                    return false;
                } else if ((new Date(fechaEla).getTime() > hoy.getTime())) {
                    alert("Fecha de fabricación no puede ser mayor a la actual");
                    return false;
                } else if ((new Date(fechaEla).getTime() > new Date(fechaNtf).getTime())) {
                    alert("Fecha de fabricación no puede ser mayor a de notificación");
                    return false;
                } else if ((new Date(fechaNtf).getTime() > new Date(fechaVcto).getTime())) {
                    alert("Fecha de notificación no puede ser mayor a la del vencimiento");
                    return false;
                } else if ((hoy.getTime() >= new Date(fechaVcto).getTime())) {
                    alert("Fecha de vencimiento no puede ser menor o igual a la fecha actual");
                    return false;
                } else if ((hoy.getTime() > new Date(fechaNtf).getTime())) {
                    alert("Fecha de notificación no puede ser menor a la fecha actual");
                    return false;
                }
            }
            ;
        </script>
    </head>

    <body>
    <center>
        <div id="main">
            <section>   	   			
                <div>

                    <form id="form1" method="POST" action="CrearProductoCtrol" onsubmit="return CompararFechas();">
                        <table width="100%">
                            <tr>
                                <td>
                                    <label for="codigoPro">Código del producto</label>
                                    <input type="text" name="codigoPro" id="codigoPro" required pattern="[a-z0-9]{5,11}" title="Ingrese un valor válido, utilizando solo carácteres alfanuméricos">
                                </td>
                                <td>
                                    <label for="nproducto">Nombre del producto</label>
                                    <input type="text" name="nproducto" id="nproducto" required pattern="[a-z]{5,21}" title="Ingrese un valor válido, sin caracteres especiales ni espacios">
                                </td>
                                <td>
                                    <label for="fechaEla">Fecha elaboración</label>
                                    <input type="date" name="fechaEla" id="fechaEla" required>
                                    <br>
                                </td>
                                <td>
                                    <label for="fechaVcto">Fecha vencimiento</label>
                                    <input type="date" name="fechaVcto" id="fechaVcto" required>
                                    <br>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="fechaVcto">Fecha Notificación</label>
                                    <input type="date" name="fechaNtf" id="fechaNtf" required>
                                    <br>
                                </td>
                                <td>
                                    <label for="marcaP">Marca</label>
                                    <input type="text" name="marcaP" id="marcaP" required pattern="[a-z]{3,11}" title="Ingrese un valor válido, solo valores alfabéticos">
                                </td>
                                <td>
                                    <label for="nlote">Numero lote</label>
                                    <input type="text" name="nlote" id="nlote" required pattern="[a-z0-9]{3,11}" title="Ingrese un valor válido">
                                    <br>
                                </td>
                                <td>
                                    <label for="vProducto">Valor producto Unidad</label>
                                    <input type="text" name="vProducto" id="vProducto" required min="0" pattern="[0-9]{3,9}" title="Ingrese un valor válido">
                                    <br>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="tip_producto">Tipo producto</label>
                                    <select name="tip_producto" required>
                                        <option value="">Seleccione el tipo de producto</option>
                                        <%while (resultado.next()) {
                                                int idTipoP = Integer.parseInt(resultado.getString("tipo_productoID"));
                                                String nombreTipoP = resultado.getString("Nom_tipoproducto");
                                        %>
                                        <option value=<%=idTipoP%> ><%=nombreTipoP%></option>
                                        <%}%>
                                    </select>
                                    <br>
                                </td>

                                <td>
                                    <label for="dproducto">Descripcion</label>
                                    <textarea name="dproducto" style="width:350px" required pattern="[a-z ]{3,18}" title="Ingrese valores alfabéticos"></textarea>
                                </td>
                                <td>   
                                    <label for="cantidadP">Cantidad</label>
                                    <input type="text" name="cantidadP" id="cantidadP" required pattern="[0-9]{1,6}" title="Ingrese un valor númerico">
                                </td>
                                <td>
                                    <label for="select_almacen">Selecione Almacen</label>
                                    <select name="select_almacen" required>
                                        <option value="">--</option>
                                        <% while (resultado3.next()) {
                                                Integer almacenId = Integer.parseInt(resultado3.getString("a.almacenID"));
                                                String ubicacionA = resultado3.getString("a.ubicacion");
                                        %>
                                        <option value=<%=almacenId%>><%=ubicacionA%></option>
                                        <%}%>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="select_proveedor">Selecione Proveedor</label>
                                    <select name="select_proveedor" required>
                                        <option value="">--</option>
                                        <% while (resultado2.next()) {
                                                String nom_prov = resultado2.getString("pr.Nom_proveedor");
                                                Integer id_prov = resultado2.getInt("pr.proveedorID");
                                        %>
                                        <option value="<%=id_prov%>"><%=nom_prov%></option>
                                        <%}%>
                                    </select>
                                </td>

                                <td>
                                    <label for="obserP">Zona o sector</label>
                                    <input type="text" name="zonaIn" required pattern="[a-z0-9]{2,5}" title="Ingrese un valor válido">
                                </td>
                                <td>
                                    <label for="obserP">estante</label>
                                    <input type="text" name="est" required pattern="[a-z0-9]{2,5}" title="Ingrese un valor válido">
                                </td>
                                <td>
                                    <label for="obserP">Nombre Inventario</label>
                                    <input type="text" name="obserP" required pattern="[a-z ]{3,11}" title="Ingrese un valor válido">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <button title="Guardar">Guardar</button>
                                </td>
                            </tr>
                            <tr>
                                <td><br><br><br></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </section>
        </div>
    </center>
</body>
</html><%}%>