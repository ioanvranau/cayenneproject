package org.example.cayenne.persistent.auto;

import org.apache.cayenne.CayenneDataObject;
import org.example.cayenne.persistent.Artist;
import org.example.cayenne.persistent.Gallery;

/**
 * Class _Painting was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Painting extends CayenneDataObject {

    public static final String NAME_PROPERTY = "name";
    public static final String ARTIST_PROPERTY = "artist";
    public static final String GALLERY_PROPERTY = "gallery";

    public static final String ID_PK_COLUMN = "ID";

    public void setName(String name) {
        writeProperty(NAME_PROPERTY, name);
    }
    public String getName() {
        return (String)readProperty(NAME_PROPERTY);
    }

    public void setArtist(Artist artist) {
        setToOneTarget(ARTIST_PROPERTY, artist, true);
    }

    public Artist getArtist() {
        return (Artist)readProperty(ARTIST_PROPERTY);
    }


    public void setGallery(Gallery gallery) {
        setToOneTarget(GALLERY_PROPERTY, gallery, true);
    }

    public Gallery getGallery() {
        return (Gallery)readProperty(GALLERY_PROPERTY);
    }


}
