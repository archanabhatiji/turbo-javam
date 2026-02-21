package com.archanabhatiji.turbojavam.runtime;

import java.io.File;
import java.io.IOException;

/**
 * Interface for loading and managing JAR files.
 * Handles JAR validation, class path resolution, and dependency management.
 */
public interface IJarLoader {
    
    /**
     * Loads a JAR file and returns the context for execution.
     *
     * @param jarFile the JAR file to load
     * @return LoadedJarContext containing JAR metadata and class information
     * @throws IOException if the file cannot be read
     * @throws InvalidJarException if the JAR is invalid or incompatible
     */
    LoadedJarContext loadJar(File jarFile) throws IOException, InvalidJarException;
    
    /**
     * Adds a library JAR to the class path.
     *
     * @param libraryJar the library JAR file to add
     * @throws IOException if the file cannot be accessed
     */
    void addToClassPath(File libraryJar) throws IOException;
    
    /**
     * Validates JAR compatibility with the runtime environment.
     *
     * @param jar the JAR file to validate
     * @throws IncompatibleJarException if the JAR is not compatible
     */
    void validateJarCompatibility(File jar) throws IncompatibleJarException;
    
    /**
     * Discovers the main class in the JAR manifest.
     *
     * @param jar the JAR file
     * @return the fully qualified main class name, or null if not found
     * @throws IOException if the manifest cannot be read
     */
    String getMainClass(File jar) throws IOException;
    
    /**
     * Clears all cached JAR data.
     */
    void clearCache();
    
    /**
     * Exception thrown when a JAR file is invalid.
     */
    class InvalidJarException extends Exception {
        public InvalidJarException(String message) {
            super(message);
        }
        
        public InvalidJarException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    /**
     * Exception thrown when a JAR is incompatible with the runtime.
     */
    class IncompatibleJarException extends Exception {
        public IncompatibleJarException(String message) {
            super(message);
        }
        
        public IncompatibleJarException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

/**
 * Context object containing loaded JAR metadata.
 */
class LoadedJarContext {
    private final File jarFile;
    private final String mainClass;
    private final java.util.List<File> dependencies;
    private final String javaVersion;
    
    public LoadedJarContext(File jarFile, String mainClass, 
                           java.util.List<File> dependencies, String javaVersion) {
        this.jarFile = jarFile;
        this.mainClass = mainClass;
        this.dependencies = dependencies;
        this.javaVersion = javaVersion;
    }
    
    public File getJarFile() { return jarFile; }
    public String getMainClass() { return mainClass; }
    public java.util.List<File> getDependencies() { return dependencies; }
    public String getJavaVersion() { return javaVersion; }
}