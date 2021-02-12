db.createUser(
    {
        user: "partner",
        pwd: "Mongo123",
        roles: [
            {
                role: "readWrite",
                db: "partner"
            }
        ]
    }
)
