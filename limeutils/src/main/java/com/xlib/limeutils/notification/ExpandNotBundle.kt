package com.xlib.limeutils.notification

data class ExpandNotBundle(
        var title: String?,
        var body: String?,
        var titleExpanded: String?,
        var bodyExpanded: String?,
        var layoutCollapsed: Int,
        var layoutExpanded: Int

)