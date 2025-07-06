
# PlanAhead Launch Script for Post-Synthesis pin planning, created by Project Navigator

create_project -name PRACTICA_7 -dir "C:/Users/pablo/OneDrive/Escritorio/P7_FC/PRACTICA_7/planAhead_run_2" -part xc3s500efg320-4
set_property design_mode GateLvl [get_property srcset [current_run -impl]]
set_property edif_top_file "C:/Users/pablo/OneDrive/Escritorio/P7_FC/PRACTICA_7/SISTEMACOMPLETO.ngc" [ get_property srcset [ current_run ] ]
add_files -norecurse { {C:/Users/pablo/OneDrive/Escritorio/P7_FC/PRACTICA_7} }
set_param project.pinAheadLayout  yes
set_property target_constrs_file "SISTEMACOMPLETO.ucf" [current_fileset -constrset]
add_files [list {SISTEMACOMPLETO.ucf}] -fileset [get_property constrset [current_run]]
link_design
