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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='salida_producto.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado, resultado1, resultado2, resultado3, resultado4;
        Statement sentencia, sentencia1, sentencia2, sentencia3, sentencia4;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        sentencia1 = (Statement) con.createStatement();
        sentencia2 = (Statement) con.createStatement();
        sentencia3 = (Statement) con.createStatement();
        sentencia4 = (Statement) con.createStatement();
        resultado = sentencia.executeQuery("SELECT * FROM producto p");;
        resultado2 = sentencia2.executeQuery("SELECT * FROM estado es");
        resultado3 = sentencia3.executeQuery("SELECT * FROM mediostransporte mp");
        resultado4 = sentencia4.executeQuery("SELECT * FROM inventario iv");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css" />
        <title>Salida de productos</title>
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <script language="javascript">
            function CompararFechas() {

                var fech = document.getElementById("trasladoP").value;
                var hoy = new Date();
                if ((new Date(fech).getTime() > hoy.getTime())) {
                    alert("Fecha de devolución no puede ser mayor a la actual");
                    return false;
                }
            }
            ;
        </script>
    </head>
    <body>
    <center>
        <form id="form1" method="POST" action="SalidaProductoCtrl" onsubmit="return CompararFechas();">
            <table>
                <tr>
                    <td>
                        <label for="trasladoP">Fecha de Traslado</label>
                        <input type="date" name="trasladoP" id="trasladoP" required/>
                    </td>
                    <td>
                        <label for="cantidadPtras">Cantidad del producto a trasladar</label>
                        <input type="number" name="cantidadPtras" id="cantidadPtras" required min="1">
                    </td>
                    <td>
                        <label for="select_inventario">Selecione Inventario</label>
                        <select name="select_inventario" required>
                            <option value="">--</option>
                            <% while (resultado4.next()) {
                                    Integer inventarioId = Integer.parseInt(resultado4.getString("iv.inventarioID"));
                                    String inventarioN = resultado4.getString("iv.Observacion");
                            %>
                            <option value=<%=inventarioId%>><%=inventarioN%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="select_estado">Selecione estado</label>
                        <select name="select_estado" required>
                            <option value="">--</option>
                            <% while (resultado2.next()) {
                                    Integer estadoID = Integer.parseInt(resultado2.getString("es.estadoID"));
                                    String estado = resultado2.getString("es.estado");
                            %>
                            <option value=<%=estadoID%>><%=estado%></option>
                            <%}%>
                        </select>
                    </td>
                    <td>
                        <label for="select_medioTransporte">Selecione Medio de transporte</label>
                        <select name="select_medioTransporte" required>
                            <option value="">--</option>
                            <% while (resultado3.next()) {
                                    Integer mediostransporteID = Integer.parseInt(resultado3.getString("mp.MediostransporteID"));
                                    String placa = resultado3.getString("mp.placa");
                            %>
                            <option value=<%=mediostransporteID%>><%=placa%></option>
                            <%}%>
                        </select>  
                        <br>
                    </td>
                    <td><br>
                        <button title="Guardar">Guardar</button>
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
<%}%>
