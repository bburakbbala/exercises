using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IPatientMedicineRepository : IRepositoryAsync<PatientMedicine>
    {
        void Update(PatientMedicine patientMedicine);
    }
}