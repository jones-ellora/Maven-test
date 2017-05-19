package systems.ellora.core.service.inventory;

import java.util.ArrayList;
import java.util.List;

import systems.ellora.core.api.inventory.Size;
import systems.ellora.core.api.inventory.SubType;
import systems.ellora.core.api.inventory.Substance;
import systems.ellora.core.api.inventory.SubstanceSize;

/*
 * Merge single Substance with multiple Sizes
 *  
 */
public class SubstanceSizeMerge {

  @SuppressWarnings("unused")

  private SubstanceService substanceService = null;

  private SizeService sizeService = null;

  public SubstanceSizeMerge(SubstanceService substanceService, SizeService sizeService) {

    this.substanceService = substanceService;
    this.sizeService = sizeService;
  }

  // Merging one substance with its multiple sizes
  public List<SubstanceSize> doMerge(List<Substance> substanceDao, List<Size> sizeDao) {
    List<SubstanceSize> finalSubstanceSize = new ArrayList<>();

    for (int i = 0; i < substanceDao.size(); i++) {

      Substance localSubstance = substanceDao.get(i);

      List<SubType> subTypes = new ArrayList<>();

      for (int j = 0; j < sizeDao.size(); j++) {

        Size localSize = sizeDao.get(j);

        if (localSubstance.getSubstanceId() == localSize.getSubstanceId()) {

          SubType localSubType = new SubType(localSize.getSize(), localSize.getQuantity(),
              localSize.getRol(), localSize.getType(), localSize.getColor(), localSize.getKey(),
              localSize.getExpiryDate());

          subTypes.add(localSubType);

        }
      }

      SubstanceSize localFinalSubstanceSize = new SubstanceSize(localSubstance.getOrganizationId(),
          localSubstance.getSubstanceId(), localSubstance.getSubstanceName(),
          localSubstance.getCategory(), subTypes);

      finalSubstanceSize.add(localFinalSubstanceSize);
    }

    return finalSubstanceSize;
  }

  // Merging single substance with its multiple sizes
  public SubstanceSize doMergeSubstanceSize(Substance substance, List<Size> size) {
    SubstanceSize substanceSize = null;

    List<SubType> subtypes = new ArrayList<>();

    for (int i = 0; i < size.size(); i++) {

      Size localSize = size.get(i);

      SubType localSubType = new SubType(localSize.getSize(), localSize.getQuantity(),
          localSize.getRol(), localSize.getType(), localSize.getColor(), localSize.getKey(),
          localSize.getExpiryDate());

      subtypes.add(localSubType);
    }

    substanceSize = new SubstanceSize(substance.getOrganizationId(), substance.getSubstanceId(),
        substance.getSubstanceName(), substance.getCategory(), subtypes);

    return substanceSize;
  }

  // Updating size after comparing existing size with updated size
  public List<SubstanceSize> updateSubstanceSize(List<SubstanceSize> existingSubstance,
      SubstanceSize[] updatedSubstance) {

    List<SubstanceSize> updatedResult = new ArrayList<>();

    for (SubstanceSize substance : existingSubstance) {

      for (int i = 0; i < updatedSubstance.length; i++) {

        if (substance.getSubstanceId() == updatedSubstance[i].getSubstanceId()) {

          for (int j = 0; j < substance.getSubType().size(); j++) {

            try {
              SubType type = substance.getSubType().get(j);

              if (type.getQuantity() != updatedSubstance[i].getSubType().get(j).getQuantity()) {

                if (!updatedResult.contains(updatedSubstance[i])) {
                  updatedResult.add(updatedSubstance[i]);
                }

              } else if (type.getRol() != updatedSubstance[i].getSubType().get(j).getRol()) {

                if (!updatedResult.contains(updatedSubstance[i])) {
                  updatedResult.add(updatedSubstance[i]);
                }

              } else if (!type.getColor()
                  .equals(updatedSubstance[i].getSubType().get(j).getColor())) {

                if (!updatedResult.contains(updatedSubstance[i])) {
                  updatedResult.add(updatedSubstance[i]);
                }

              }

              sizeService.updateSize(substance.getOrganizationId(), substance.getSubstanceId(),
                  updatedSubstance[i].getSubType().get(j).getSizekey(),
                  updatedSubstance[i].getSubType().get(j).getQuantity(),
                  updatedSubstance[i].getSubType().get(j).getRol(),
                  updatedSubstance[i].getSubType().get(j).getColor());

            } catch (Exception e) {

              e.printStackTrace();
            }
          }
        }
      }
    }
    return updatedResult;
  }
}
