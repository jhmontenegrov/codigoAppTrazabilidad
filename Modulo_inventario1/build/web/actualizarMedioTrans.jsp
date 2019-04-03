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
    resultaLog=sent.executeQuery("SELECT u.UsuarioID FROM usuario u, asignada_perfil_usuario apu, actividades_perfil_usuario ap WHERE u.UsuarioID='"+idUsuario+"' AND u.UsuarioID=apu.UsuarioID AND apu.Actividades_perfil_usuarioID=ap.Actividades_perfil_usuarioID AND ap.Url='ingresar_medioTrans.jsp'");
//recuperar la sesión
    if (!resultaLog.next()) {
        request.getRequestDispatcher("/errorDeAcceso.jsp").forward(request, response);
    } else {
        ResultSet resultado;
        Statement sentencia;
        Connection con;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        Integer idMediot = Integer.parseInt(request.getParameter("Id"));
        resultado = sentencia.executeQuery("SELECT * FROM MediosTransporte mt where MediostransporteID='" + idMediot + "'");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualiza Medio de transporte</title>
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <script language="javascript" src="js/jquery-3.2.1.min.js"></script>
        <link rel="stylesheet" href="css/main.css" />
    </head>
    <body>
        <% while (resultado.next()) {
                String marcaMt = resultado.getString("mt.marca");
                String modeloMt = resultado.getString("mt.modelo");
                String placaMt = resultado.getString("mt.placa");
                Integer idto = resultado.getInt("mt.MediostransporteID");
        %>
    <center>
        <form id="form1" method="POST" action="ActualizamedioTransCtrl">
            <input type="hidden" name="idt" id="idt" value="<%=idto%>">
            <table>
                <td>
                    <br>
                    <label for="marcar">Marca</label>
                    <input type="text" name="marcar" id="marcar" value="<%=marcaMt%>" required pattern="[a-z0-9 ]{3,31}" title="Ingrese un valor válido" required>
                    <br>
                    <label for="modelCar">Modelo</label>
                    <input type="text" name="modelCar" id="modelCar" value="<%=modeloMt%>" required pattern="[a-z0-9 ]{3,31}" title="Ingrese un valor válido" required>
                    <br>
                    <label for="placaCar">Placa</label>
                    <input type="text" name="placaCar" id="placaCar" value="<%=placaMt%>" disabled>
                    <br>
                    <button title="Actualizar">Actualizar</button>
                </td>
            </table>
            <%}%>
        </form>
    </center>
</body>
</html>
<%}%>