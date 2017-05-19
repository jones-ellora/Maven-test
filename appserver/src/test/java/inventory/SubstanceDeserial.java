package inventory;

//
//
// import static io.dropwizard.testing.FixtureHelpers.fixture;
// import static org.assertj.core.api.Assertions.assertThat;
//
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
// import java.util.List;
//
// import org.junit.Test;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
//
// import ellora.inventory.substance.SubType;
// import ellora.inventory.substance.SubstanceSize;
// import io.dropwizard.jackson.Jackson;
//
// public class SubstanceDeserial {
// private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
//
//
//
// @Test
// public void deserializesFromJSON() throws Exception {
//
// String startDate="2058-01-05";
// SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
// java.util.Date date = sdf1.parse(startDate);
// java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
//
// List<SubType> lisOfSubType = new ArrayList<>();
//
// SubType subType = new SubType(1, 94, 20, "Small", "Brown", "106-1",sqlStartDate);
// lisOfSubType.add(subType);
//
// subType = new SubType(2, 546, 20, "Medium", "Brown", "106-2",sqlStartDate);
// lisOfSubType.add(subType);
//
// subType = new SubType(3, 554, 20, "Large", "Brown", "106-3",sqlStartDate);
// lisOfSubType.add(subType);
//
// final SubstanceSize person = new SubstanceSize(01,106,"Bandages","Material",lisOfSubType);
//
// assertThat(MAPPER.readValue(fixture("fixtures/substance.json"), SubstanceSize.class))
// .isEqualToComparingFieldByField(person);
// }
// }
