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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='crearrequisicion.jsp'");
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
    Integer idDev = Integer.parseInt(request.getParameter("Id"));
    resultado = sentencia.executeQuery("SELECT * FROM requisicion r, estado es WHERE r.RequisicionID='" + idDev + "' AND es.EstadoID=r.EstadoID");
    resultado1 = sentencia1.executeQuery("SELECT * FROM estado est");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualiza Proveedor</title>
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <link rel="stylesheet" href="css/main.css" />
    </head>
    <body>
        <% while (resultado.next()) {
                String NomProd = resultado.getString("r.Nombre_producto");
                Date fech = resultado.getDate("r.Fecha_solicitud");
                String cantPro = resultado.getString("r.Cantidad");
                Integer estId = resultado.getInt("r.EstadoID");
                String estNom = resultado.getString("es.Estado");
        %>
        <form id="form1" method="POST" action="ActualizarPedidoCtrl">
            <table style="width: 100%">
                <tr>
                    <td>
                        <input type="hidden" name="idDev" value="<%=idDev%>"/>
                    </td>
                    <td>
                        <label for="ubicA">Producto</label>
                        <input type="text" name="prNom" value="<%=NomProd%>" disabled/>
                    </td>
                    <td>
                        <label for="ubicA">Cantidad:</label>
                        <input type="text" name="cant" value="<%=cantPro%>" disabled/>
                    </td>
                    <td>
                        <label for="ubicA">Fecha que llega solicitud:</label>
                        <input type="date" name="fech" value="<%=fech%>" disabled/>
                    </td>
                    <td>
                        <label for="estID">Estado:</label>
                        <select name="estID" required>
                            <option value="<%=estId%>"><%=estNom%></option>
                            <%} while (resultado1.next()) {
                                    Integer estaID = resultado1.getInt("est.EstadoID");
                                    String estaUb = resultado1.getString("est.Estado");
                            %>
                            <option value=<%=estaID%> ><%=estaUb%> </option>
                            <%}%>
                        </select>
                    </td>

                </tr>
                <tr>
                    <td>
                        <br>
                        <button title="Actualizar">Actualizar</button>               
                    </td>
                </tr>
            </table>
        </form><%}%>
    </body>
</html>
