// include Fake lib
#r "../tools/fake/FakeLib.dll"
open Fake

// Environment
let ilMergePath = "../tools/ilmerge.exe"

// Filesets
let projectFile = "../bflow.setup/projects/bflow.setup/bflow.setup.csproj"

// Target directories
let targetDir = "../.binaries/"

// Targets
Target "CleanTargetDir" (fun _ -> 
    CleanDir targetDir
)

Target "PackageRestore" (fun _ ->
    ()
)

Target "Build client" (fun _ -> 
    MSBuildRelease targetDir "Build" [projectFile] |> Log "Build: "
)

Target "Zip files" (fun _ ->
    let zipFn = "bflow-setup.zip"
    let files = !!(combinePaths targetDir "*.*")
    Zip targetDir zipFn files
    MoveFile targetDir zipFn
)

Target "Default" (fun _ -> 
    ()
)

// Dependencies
"CleanTargetDir"
    ==> "PackageRestore"
    ==> "Build client"
    ==> "Zip files"
    ==> "Default"

// start build
RunTargetOrDefault "Default"