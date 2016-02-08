package org.example.cayenne;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;
import org.example.cayenne.persistent.Artist;
import org.example.cayenne.persistent.Gallery;
import org.example.cayenne.persistent.Painting;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * Created by ioan.vranau on 2/8/2016.
 */
public class Main {
    public static void main(String[] args) {
        ServerRuntime cayenneRuntime = new ServerRuntime(
                "cayenne-project.xml");
        ObjectContext context = cayenneRuntime.getContext();


        Artist picasso = context.newObject(Artist.class);
        picasso.setName("Pablo Picasso");
        picasso.setDateOfBirthString("18811025");

        Gallery metropolitan = context.newObject(Gallery.class);
        metropolitan.setName("Metropolitan Museum of Art");

        Painting girl = context.newObject(Painting.class);
        girl.setName("Girl Reading at a Table");

        Painting stein = context.newObject(Painting.class);
        stein.setName("Gertrude Stein");

        picasso.addToPaintings(girl);
        picasso.addToPaintings(stein);

        girl.setGallery(metropolitan);
        stein.setGallery(metropolitan);

        context.commitChanges();


        //select examples

        SelectQuery select1 = new SelectQuery(Painting.class);
        List paintings1 = context.performQuery(select1);

        Expression qualifier2 = ExpressionFactory.likeIgnoreCaseExp(
                Painting.NAME_PROPERTY,
                "gi%");
        SelectQuery select2 = new SelectQuery(Painting.class, qualifier2);
        List paintings2 = context.performQuery(select2);
        for (Object o : paintings2) {
            System.out.println("----------"+o.toString());
        }


        Calendar c = new GregorianCalendar();
        c.set(c.get(Calendar.YEAR) - 100, 0, 1, 0, 0, 0);

        Expression qualifier3 = Expression.fromString("artist.dateOfBirth < $date");
        qualifier3 = qualifier3.expWithParameters(Collections.singletonMap("date", c.getTime()));
        SelectQuery select3 = new SelectQuery(Painting.class, qualifier3);
        List paintings3 = context.performQuery(select3);

    }
}
