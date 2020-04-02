package boni.sample.kotlin.library.paging

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 데이터 클래스
 */
@Entity
data class Paging (@PrimaryKey(autoGenerate = true) val id: Int, val name: String)
