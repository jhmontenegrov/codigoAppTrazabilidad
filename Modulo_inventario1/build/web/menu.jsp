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
    resultaLog=sent.executeQuery("SELECT UsuarioID FROM usuario WHERE UsuarioID='"+idUsuario+"'");
//recuperar la sesión
    if (idUsuario==null) {
        request.getRequestDispatcher("/Inicio_sesion.jsp").forward(request, response);
    } else {
        ResultSet resultado,resultado1,resultado2,resultado3,resultado4,resultado5;
        Statement sentencia;
        Statement sentencia1,sentencia2,sentencia3,sentencia4,sentencia5;
        Connection con;
        String actividades;
        con = (Connection) Conexion.crearConexion();
        sentencia = (Statement) con.createStatement();
        sentencia1 = (Statement) con.createStatement();
        sentencia2 = (Statement) con.createStatement();sentencia3 = (Statement) con.createStatement();sentencia4 = (Statement) con.createStatement();sentencia5 = (Statement) con.createStatement();
        resultado = sentencia.executeQuery("SELECT * FROM actividades_perfil_usuario ap, asignada_perfil_usuario apu, usuario us, activacion activ where us.usuarioID='" + idUsuario + "' AND us.UsuarioID=apu.UsuarioID AND ap.Actividades_perfil_usuarioID=apu.Actividades_perfil_usuarioID and Posicion_menu=1 and apu.ActivacionID=activ.ActivacionID and Nombre_activacion='Activado'");
        resultado1 = sentencia1.executeQuery("SELECT * FROM actividades_perfil_usuario ap, asignada_perfil_usuario apu, usuario us, activacion activ where us.usuarioID='" + idUsuario + "' AND us.UsuarioID=apu.UsuarioID AND ap.Actividades_perfil_usuarioID=apu.Actividades_perfil_usuarioID and Posicion_menu=2 and apu.ActivacionID=activ.ActivacionID and Nombre_activacion='Activado'");
        resultado2 = sentencia2.executeQuery("SELECT * FROM actividades_perfil_usuario ap, asignada_perfil_usuario apu, usuario us, activacion activ where us.usuarioID='" + idUsuario + "' AND us.UsuarioID=apu.UsuarioID AND ap.Actividades_perfil_usuarioID=apu.Actividades_perfil_usuarioID and Posicion_menu=3 and apu.ActivacionID=activ.ActivacionID and Nombre_activacion='Activado'");
        resultado3 = sentencia3.executeQuery("SELECT * FROM actividades_perfil_usuario ap, asignada_perfil_usuario apu, usuario us, activacion activ where us.usuarioID='" + idUsuario + "' AND us.UsuarioID=apu.UsuarioID AND ap.Actividades_perfil_usuarioID=apu.Actividades_perfil_usuarioID and Posicion_menu=4 and apu.ActivacionID=activ.ActivacionID and Nombre_activacion='Activado'");
        resultado4 = sentencia4.executeQuery("SELECT * FROM actividades_perfil_usuario ap, asignada_perfil_usuario apu, usuario us, activacion activ where us.usuarioID='" + idUsuario + "' AND us.UsuarioID=apu.UsuarioID AND ap.Actividades_perfil_usuarioID=apu.Actividades_perfil_usuarioID and Posicion_menu=5 and apu.ActivacionID=activ.ActivacionID and Nombre_activacion='Activado'");
        resultado5 = sentencia5.executeQuery("SELECT * FROM actividades_perfil_usuario ap, asignada_perfil_usuario apu, usuario us, activacion activ where us.usuarioID='" + idUsuario + "' AND us.UsuarioID=apu.UsuarioID AND ap.Actividades_perfil_usuarioID=apu.Actividades_perfil_usuarioID and Posicion_menu=6 and apu.ActivacionID=activ.ActivacionID and Nombre_activacion='Activado'");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/main.css"/>
        <script src="js/jquery.min.js"></script>
        <script src="js/skel.min.js"></script>
        <script src="js/util.js"></script>
        <script src="js/main.js"></script>
        <title>Menu</title>
    </head>
    <body>
        <div id="sidebar2">
            <nav id="menu" class="cargaP2">
                <header class="major">
                    <h2>Menu</h2>
                </header>
                <ul>
                    <li>
                        <span class="opener">Producto</span>
                        <ul>

                            <%while (resultado.next()) {
                                    actividades = resultado.getString("Actividades");
                                    String url = resultado.getString("URL");
                            %>
                            <li><a href="<%=url%>" target="cargapa"><%=actividades%></a></li>
                                <%}%>
                        </ul>
                    </li>
                    <li>
                        <span class="opener">Inventario</span>
                        <ul>

                            <%while (resultado1.next()) {
                                    actividades = resultado1.getString("Actividades");
                                    String url = resultado1.getString("URL");
                            %>
                            <li><a href="<%=url%>" target="cargapa"><%=actividades%></a></li>
                                <%}%>
                        </ul>
                    </li>
                    <li>
                        <span class="opener">Administrar Usuarios</span>
                        <ul>

                            <%while (resultado2.next()) {
                                    actividades = resultado2.getString("Actividades");
                                    String url = resultado2.getString("URL");
                            %>
                            <li><a href="<%=url%>" target="cargapa"><%=actividades%></a></li>
                                <%}%>
                        </ul>
                    </li>
                    <li>
                        <span class="opener">Almacenaje</span>
                        <ul>

                            <%while (resultado3.next()) {
                                    actividades = resultado3.getString("Actividades");
                                    String url = resultado3.getString("URL");
                            %>
                            <li><a href="<%=url%>" target='cargapa'><%=actividades%></a></li>
                                <%}%>
                        </ul>
                    </li>
                    <li>
                        <span class="opener">Estados</span>
                        <ul>

                            <%while (resultado4.next()) {
                                    actividades = resultado4.getString("Actividades");
                                    String url = resultado4.getString("URL");
                            %>
                            <li><a href="<%=url%>" target="cargapa"><%=actividades%></a></li>
                                <%}%>
                        </ul>
                    </li>
                    <li>
                        <span class="opener">Proveedor</span>
                        <ul>

                            <%while (resultado5.next()) {
                                    actividades = resultado5.getString("Actividades");
                                    String url = resultado5.getString("URL");
                            %>
                            <li><a href="<%=url%>" target="cargapa"><%=actividades%></a></li>
                                <%}%>
                        </ul>
                    </li>
                    
                </ul>
                <!-- Footer -->
            </nav>
            <footer id="footer">
                <p class="copyright">&copy; Untitled. All rights reserved. Demo Images: <a href="https://unsplash.com">Unsplash</a>. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
            </footer>
        </div>
        <%}%>
    </body>
</html>
