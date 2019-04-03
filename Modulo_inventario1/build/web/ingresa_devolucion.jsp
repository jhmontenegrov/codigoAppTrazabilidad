<%@page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="errorPage.jsp" %>
<%@page import ="beans.Conexion" %>
<%@page import="java.sql.Statement"%>
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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='ingresa_devolucion.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado, resultado1;
        Statement sentencia, sentencia1;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        sentencia1 = (Statement) con.createStatement();
        resultado = sentencia.executeQuery("SELECT * FROM estado est");
        resultado1 = sentencia1.executeQuery("SELECT * FROM Inventario iv");
%>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <link rel="stylesheet" href="css/main.css"/>
        <title>Actualiza Proveedor</title>
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <script>
            function CompararFechas() {

                var fech = document.getElementById("fech").value;
                var hoy = new Date();
                if ((new Date(fech).getTime() > hoy.getTime())) {
                    alert("Fecha de devolución no puede ser mayor a la actual"+new Date(fech).getTime()+"  "+hoy.getTime());
                    return false;
                }
            };
        </script>
        <link rel="stylesheet" href="css/main.css" />
    </head>
    <body>
        <form id="form1" method="POST" action="IngresarDevolucionCtrl" onsubmit="return CompararFechas();">
            <table style="width: 100%">
                <tr>
                    <td>
                        <label for="select_inventario">Inventario:</label>
                        <select name="select_inventario" required>
                            <option value="">--</option>
                            <%while (resultado1.next()) {
                                    Integer estaID = resultado1.getInt("iv.InventarioID");
                                    String estaUb = resultado1.getString("iv.Observacion");
                            %>
                            <option value=<%=estaID%> ><%=estaUb%> </option>
                            <%}%>
                        </select>
                    </td>
                    <td>
                        <label for="cant">Cantidad reingreso</label>
                        <input type="number" name="cant" min="0" required pattern="[0-9]{1,5}" title="Ingrese un valor válido"/>
                    </td>
                    <td>
                        <label for="fech">Fecha devolución:</label>
                        <input type="date" name="fech" id="fech" required/>
                    </td>
                    <td>
                        <label for="estID">Estado:</label>
                        <select name="estID" required>
                            <option value="">--</option>
                            <%while (resultado.next()) {
                                    Integer estaID = resultado.getInt("est.EstadoID");
                                    String estaUb = resultado.getString("est.Estado");
                            %>
                            <option value=<%=estaID%> ><%=estaUb%> </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <br>
                        <button title="Guardar">Guardar</button>               
                    </td>
                </tr>
            </table>
        </form><%}%>
    </body>
</html>

