package emerge.project.onmealoutlet.data.db;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by Himanshu Emerge on 3/2/2018.
 */

public class RealmMigrations implements RealmMigration {


    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema realmSchema = realm.getSchema();
        for (long version = oldVersion; version < newVersion; version++) {
            if (version == 0) { // to 1

            }
            if (version == 1) { // to 1
                final RealmObjectSchema userSchema = realmSchema.get("Outlet");
                userSchema.addField("userID", int.class);
            }
        }
    }



}
