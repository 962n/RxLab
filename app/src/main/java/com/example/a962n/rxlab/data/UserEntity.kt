package com.example.a962n.rxlab.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "users"
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "first_name")
    val firstName: String?,
    @ColumnInfo(name = "last_name")
    val lastName: String?
)
{
    companion object {
        fun create4Insert(firstName: String?,lastName: String?) : UserEntity {
            return UserEntity(0,firstName,lastName)
        }
    }
    override fun toString(): String {
        return "UserEntity(id=$id, firstName=$firstName, lastName=$lastName)"
    }
}