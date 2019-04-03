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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='ingresar_establecimiento.jsp'");
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
        Integer idEst = Integer.parseInt(request.getParameter("Id"));

        resultado = sentencia.executeQuery("SELECT * FROM Establecimiento est, ciudad c where est.EstablecimientoID='" + idEst + "' AND est.ciudadID=c.ciudadID");
        resultado1 = sentencia1.executeQuery("SELECT * FROM ciudad ci");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualiza Establecimiento</title>
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <link rel="stylesheet" href="css/main.css" />
    </head>
    <body>
        <% while (resultado.next()) {
                String ubicacion = resultado.getString("est.Ubicaciontienda");
                Integer idciudad = resultado.getInt("est.CiudadID");
                String ciudadnom = resultado.getString("c.Ciudad_nombre");
                String ciudadPais = resultado.getString("c.pais");
        %>
    <center>
        <form id="form1" method="POST" action="ActualizarestablecimientoCtrl">
            <table>
                <input type="hidden" name="idEst1" id="idt" value="<%=idEst%>" >
                <br>
                <label for="ubica">Ubicación</label>
                <input type="text" name="ubica" id="ubica" value="<%=ubicacion%>" required pattern="[a-z0-9 |-]{4,32}" title="Ingrese un valor válido">
                <br>
                <label for="id_ciudadAC">Ciudad</label>
                <select name="id_ciudadAC" required>
                    <option value="<%=idciudad%>"><%=ciudadnom%> - <%=ciudadPais%></option>
                    <% }
                        while (resultado1.next()) {
                            Integer id_ciudad = resultado1.getInt("ci.CiudadID");
                            String ciudad = resultado1.getString("ci.Ciudad_nombre");
                            String ciudadPais = resultado1.getString("ci.Pais");
                    %>
                    <option value=<%=id_ciudad%> ><%=ciudad%> - <%=ciudadPais%></option>
                    <%}
                    }%>
                </select><br>
                <button title="Actualizar">Actualizar</button>
            </table>
        </form>
    </center>
</body>
</html>

