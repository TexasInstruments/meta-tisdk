# Default to software renderer for all TI SDK targets.
SLINT_RENDERER = "software"

# Enable LTO for software renderer builds (no Skia = no RAM pressure).
# Gives 5-15% runtime improvement, especially beneficial on Cortex-A53
do_compile:prepend() {
    export CARGO_PROFILE_RELEASE_LTO=true
}
