package id.ac.unpas.tokoelektronik.networks

import id.ac.unpas.tokoelektronik.model.Periferal

data class PeriferalGetResponse(
    val data: List<Periferal>? = null
)