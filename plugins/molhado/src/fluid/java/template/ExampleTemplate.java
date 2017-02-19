/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/template/ExampleTemplate.java,v 1.1 2006/03/21 23:21:00 dig Exp $ */
package fluid.java.template;

import fluid.java.operator.FloatLiteral;
import fluid.java.operator.ForStatement;
import fluid.java.operator.IntLiteral;
import fluid.template.Field;
import fluid.template.PartialTemplate;
import fluid.template.StringField;
import fluid.template.StringVectorField;
import fluid.template.TemplateEvent;
import fluid.tree.Operator;
import fluid.version.Version;
import fluid.version.VersionCursor;

public class ExampleTemplate
extends PartialTemplate
{
  private static final Operator[] ops1 = { IntLiteral.prototype };
  private static final Operator[] ops2 = { ForStatement.prototype };
  private static final Operator[] ops3 = { IntLiteral.prototype, FloatLiteral.prototype };
  private static final Operator[][] oplist = { ops1, ops1 };

  private final Field intExp;
  private final Field forStmt;
  private final Field intOrFloat;
  private final Field string;
  private final Field stringVector;
  private final Field nodeVector;

  public ExampleTemplate( String name )
  {
    super( name, new VersionCursor( Version.getVersion() ) );

    intExp = new JavaNodeField( "Field 1 (Int)", this, ops1 );
    forStmt = new JavaNodeField( "Field 2 (For)", this, ops2 );
    intOrFloat = new JavaNodeField( "Field 3 (Int/Float)", this, ops3 );
    string = new StringField( "Field 4 (User String)", this );
    stringVector = new StringVectorField( "Field 5 (String Vector)", this );
    nodeVector = new JavaNodeVectorField( "Field 6 (i,i,(i|f)*)", this,
                                         Field.USE_TEMPLATES_TRACKER,
                                         Field.DEFAULT_CONSULTANT, 5,
                                         oplist, ops3 );
    fields = new Field[] { intExp, forStmt, intOrFloat, string, stringVector, nodeVector };
  }

  public boolean readyToRun()
  {
    boolean ready = true;
    for( int i = 0; i < fields.length && ready; i++ )
      if( fields[i].isEmpty() ) ready = false;

    // Check to see that the string sequence has at least 3 elements
    if( ready )
    {
      final Object[] v = fields[4].getValue();
      if( v.length < 3 ) ready = false;
    }
    return ready;
  }

  protected TemplateEvent runImpl() {
    return new TemplateEvent.TemplateDoneEvent( this, true, "Completed Successfully." );
  }
}