/* $Header: /usr/local/refactoring/molhadoRef/src/fluid/java/template/SimpleTemplate.java,v 1.1 2006/03/21 23:21:00 dig Exp $ */
package fluid.java.template;

import fluid.java.operator.FloatLiteral;
import fluid.java.operator.IntLiteral;
import fluid.template.BooleanField;
import fluid.template.Field;
import fluid.template.FieldConsultant;
import fluid.template.IntegerField;
import fluid.template.PartialTemplate;
import fluid.template.StringField;
import fluid.template.StringVectorField;
import fluid.template.TemplateEvent;
import fluid.tree.Operator;
import fluid.version.VersionTracker;

public class SimpleTemplate
extends PartialTemplate
implements FieldConsultant
{
  private static final Operator[] ops3 = { IntLiteral.prototype, FloatLiteral.prototype };

  private final Field string1;
  private final Field string2;
  private final Field bool;
  private final Field integer;
  private final Field stringVector;
  private final Field intOrFloat;

  public SimpleTemplate( final String name, final VersionTracker vt )
  {
    super( name, vt );
    
    string1 = new StringField( "String1", this );
    string2 = new StringField( "String2", this, this );
    bool = new BooleanField( "Boolean", this );
    integer = new IntegerField( "Integer", this );
    stringVector = new StringVectorField( "String Vector", this, this );
    intOrFloat = new JavaNodeField( "int/float", this, ops3 );

    fields = new Field[] { string1, string2, bool, integer, stringVector, intOrFloat };
  }

  public boolean isObjectAcceptable( final Field f, final int pos, final Object o )
  {
    final String s = (String)o;
    return ((s.length() & 0x1) == 0);
  }

  public boolean isObjectAcceptable( final Field f, final Object[] o )
  {
    for( int i = 0; i < o.length; i++ )
      if( (((String)o[i]).length() & 0x1) != 0 )
        return false;
    return true;
  }

  public boolean readyToRun()
  {
    boolean ready = true;
    for( int i = 0; i < fields.length && ready; i++ )
      if( fields[i].isEmpty() ) ready = false;
    return ready;
  }

  protected TemplateEvent runImpl() {
    return new TemplateEvent.TemplateDoneEvent( this, true, "Completed Successfully." );
  }
}