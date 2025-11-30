# remove the module from autoload to be able to load it manually
# due to a hardware quirk, and the loading of the module is taken 
# care by the host_loading_service binary supplied by Lumissil
KERNEL_MODULE_AUTOLOAD:remove = "${MODULE_NAME}"

PR:append = "_tisdk_0"
