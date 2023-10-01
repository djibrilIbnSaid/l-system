package graphique;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.*;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.gl2.GLUT;
import java.util.Arrays;

/**
 * Rendu3D cette classe permet d'afficher le rendu en 2D !
 * 
 * @author  Elhadj Alseiny20
 * @version 2.0
 * @since   2022-04-04
 */
public class Rendu3D extends JPanel implements GLEventListener, MouseMotionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float rotateX, rotateY;
    private int lastX, lastY,recopie=0;
    private final float angle;
	public float scale=0.15f;
    public GLCanvas glCanvas;
    private String chemin="";
    private final String code;
    ArrayList<String> listeSauvegarde = new ArrayList<>();
    private boolean sauvegarde = false,reset = false; 
    ArrayList <String> signes =new ArrayList<>(Arrays.asList("+", "-", "<", ">","B","H"));
    ArrayList <String> axioms =new ArrayList<>(Arrays.asList("F","X","N","P"));

    public Rendu3D(String code,float angle) {
    	this.code = code;
    	this.angle =angle;
        int taille = code.length();
        while (taille>0){
            listeSauvegarde.add("");
            taille--;
        }
        this.setPreferredSize(new Dimension (700,700));
        GLProfile glProfile = GLProfile.getDefault();
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        glCapabilities.setDoubleBuffered(true);
        glCanvas = new GLCanvas(glCapabilities);
        glCanvas.addGLEventListener(this);
        glCanvas.addMouseMotionListener(this);
        add(glCanvas);
        addMouseMotionListener(this);
        rotateX = 0f; rotateY = 0f;
        Animator a = new Animator(glCanvas);
        a.start();
    } 
    
    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.8F, 0.8F, 0.8F, 1.0F);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        gl.glEnable(GL2.GL_NORMALIZE);
    }

    /**
	 * Cette fonction permet d'afficher l'arbre en 2D
	 * @param drawable est un objet de type GLAutoDrawable
	 */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0,0,0,0);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glTranslatef(0f,-0.9f,0f);
        gl.glScalef(this.scale, this.scale, this.scale);
        gl.glScalef(2f,2f,2f);
        gl.glPushMatrix();
        gl.glOrtho(-1,1,-1,1,-1,1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glRotatef(rotateX,0,1,0);
        gl.glRotatef(rotateY-90,1,0,0);


        String[] tabCode = this.code.split("");
        ArrayList<String> rotation = new ArrayList<>();
        for(String c : tabCode) {
            if(c.equals("+") ) {
                rotation.add("+y");
            } else if (c.equals("-")) {
                rotation.add("-y");
            } else if (c.equals("<")) {
                rotation.add("-z");
            } else if (c.equals(">")) {
                rotation.add("+z");
            } else if (c.equals("B")) {
                rotation.add("-x");
            } else if (c.equals("H")) {
                rotation.add("+x");
            } else if (c.equals("[")) {
            	this.recopie++;
                this.chemin= c+this.chemin;
                this.sauvegarde = true;
                this.reset=false;
            } else if (c.equals("]")) {
            	this.sauvegarde = false;
                this.reset = true;
            }else if (axioms.contains(c)){
                gl.glTranslatef(0f,0f, 0.1f);
                for (String rot: rotation){
                    switch (rot) {
                        case "+x":
                            gl.glRotatef(angle, 1, 0, 0);
                            break;
                        case "-x":
                            gl.glRotatef(-angle, 1, 0, 0);
                            break;
                        case "-y":
                            gl.glRotatef(angle, 0, 1, 0);
                            break;
                        case "+y":
                            gl.glRotatef(-angle, 0, 1, 0);
                            break;
                        case "-z":
                            gl.glRotatef(-angle, 0, 0, 1);
                            break;
                        case "+z":
                            gl.glRotatef(angle, 0, 0, 1);
                            break;
                    }
                }
                rotation.clear();
                gl.glColor3f(0, 1, 0);
                (new GLUT()).glutSolidCylinder(0.01, 0.1, 10, 5);            }
            if (sauvegarde || recopie>0 && !c.equals("]")){
                this.listeSauvegarde.set(recopie,c+listeSauvegarde.get(recopie));
            }else if (reset){
                String[] tabsauvegarde = permitationChemin(recopie);
                this.listeSauvegarde.set(recopie,"");
                if (recopie>0)
                    this.recopie--;
                this.chemin="";
                for(String s : tabsauvegarde) {
                    if(axioms.contains(s)) {
                        gl.glTranslatef(0, 0, -0.1f);
                    }else if(s.equals("+")) {
                        gl.glRotatef(this.angle, 0,1f,0f);
                    } else if (s.equals("-")) {
                        gl.glRotatef(-this.angle, 0,1f,0f);
                    }else if(s.equals(">")) {
                        gl.glRotatef(-this.angle, 0,0,1f);
                    } else if (s.equals("<")) {
                        gl.glRotatef(+this.angle, 0,0f,1f);
                    }else if(s.equals("H")) {
                        gl.glRotatef(-this.angle, 1f,0f,0f);
                    } else if (s.equals("B")) {
                        gl.glRotatef(+this.angle, 1f,0f,0f);
                    }
                }
            }
     
        }
    }

    /**
	 * traitement sur les bloc de branche sauvegarder.
	 * cette fonction positionne les angles de rotation avant les lignes
	 * @return position 
	 * @return renvoie le code de la branche sauvegarder renversé.
	 */
    private String[] permitationChemin(int position) {
        String[] tabsauvegarde = this.listeSauvegarde.get(position).split("");
        for (int v=0; v<tabsauvegarde.length;v++){
            String caract= tabsauvegarde[v];
            if (axioms.contains(tabsauvegarde[v])&& (signes.contains(tabsauvegarde[v+1]))){
                int k=v;
                while (signes.contains(tabsauvegarde[k+1])){
                    tabsauvegarde[k]=tabsauvegarde[k+1];
                    k++;
                }
                tabsauvegarde[k]=caract;
            }
        }
        return tabsauvegarde;
    }
    
    @Override
    public void reshape(
            GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();     
    }
    
    /**
	 *     fonction de controle de camera
	 *     @param e est un MouseEvent
	 */
    @Override
    public void mouseDragged(MouseEvent e) {
        rotateX += e.getX() - lastX;
        rotateY += e.getY() - lastY;
        lastX = e.getX();
        lastY = e.getY();
    }
}

