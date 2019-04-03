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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='crearrequisicion.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado, resultado2;
        Statement sentencia, sentencia2;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        sentencia2 = (Statement) con.createStatement();
        resultado2 = sentencia2.executeQuery("SELECT * FROM Proveedor");;
        resultado = sentencia.executeQuery("SELECT * FROM Estado");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <link rel="stylesheet" href="css/main.css"/>
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <script language="javascript">
            function compararFechas() {
                var fech = document.getElementById("fecha_solicutud").value;
                var hoy = new Date();
                if ((new Date(fech).getTime() > hoy.getTime())) {
                    alert("Fecha de devolución no puede ser mayor a la actual");
                    return false;
                } else if (new Date(fech).getTime() < hoy.getTime())) {
                    alert("Fecha de devolución no puede ser menor a la actual");
                    return false;
                }
            }
            ;
        </script>
    </head>
    <body>
        <form id="form1" method="POST" action="CrearRequisicionCtrl" onsubmit="return compararFechas();">
            <table>
                <tr>
                    <td>
                        <label for="nombre_producto">Nombre del producto</label>
                        <input type="text" name="nombre_producto" id="Nombre_producto" required pattern="[a-z ]{3,31}" title="Solo ingrese letras sin tildes">
                    </td>
                    <td>
                        <label for="fecha_solicitud">Fecha solicitud</label>
                        <input type="date" name="fecha_solicutud" id="fecha_solicutud" required >
                    </td>
                    <td>
                        <label for="cantidadre">Cantidad</label>
                        <input type="number" name="cantidadre" id="cantidadre" required min="0" pattern="[0-9]{1,10}" title="Solo ingrese letras sin tildes">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="estadoID">Estado del pedido</label>
                        <select name="estadoID" required>
                            <option value="">--</option>
                            <% while (resultado.next()) {
                                    Integer id_estado = Integer.parseInt(resultado.getString("EstadoID"));
                                    String estado = resultado.getString("Estado");
                            %>
                            <option value=<%=id_estado%>><%=estado%></option>
                            <%}%>
                        </select>
                    </td>
                    <td>
                        <label for="proveedorID">Proveedor</label>
                        <select name="proveedorID" required>
                            <option value="">--</option>
                            <% while (resultado2.next()) {
                                    Integer id_proveedor = Integer.parseInt(resultado2.getString("ProveedorID"));
                                    String proveedor = resultado2.getString("Nom_proveedor");
                            %>
                            <option value=<%=id_proveedor%>><%=proveedor%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="objservacion">Descripción </label>
                        <textarea name="objservacion" style="width:350px" required pattern="[a-z ]{3,31}" title="Solo ingrese letras sin tildes"></textarea>
                    </td>
                    <td>
                        <br>
                        <button title="Guardar">Guardar</button>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html><%}%>
