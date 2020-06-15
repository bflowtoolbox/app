// include Fake lib
#r "../tools/fake/FakeLib.dll"
open Fake

// Environment
let ilMergePath = "../tools/ilmerge.exe"

// Filesets
let projectFile = "../bflow.setup/projects/bflow.setup/bflow.setup.csproj"
let tgtPackFile = "../bflow.setup/projects/bflow.setup/zippacks/bflow.zip"
let srcPackFile = "../packs/bflow-1.5.0.zip"

// Target directories
let targetDir = "../.binaries/"

// Targets
Target "Clean Target Dir" (fun _ -> 
    CleanDir targetDir
)

Target "Restore Packages" (fun _ ->
    ()
)

Target "Copy Packs" (fun _ -> 
    CopyFile tgtPackFile srcPackFile
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
"Clean Target Dir"
    ==> "Restore Packages"
    ==> "Copy Packs"
    ==> "Build client"
    ==> "Zip files"
    ==> "Default"

// start build
RunTargetOrDefault "Default"